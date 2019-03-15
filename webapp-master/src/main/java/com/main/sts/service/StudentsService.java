package com.main.sts.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.StudentsDao;
import com.main.sts.entities.Address;
import com.main.sts.entities.AdminEmails;
import com.main.sts.entities.Alerts;
import com.main.sts.entities.Buses;
import com.main.sts.entities.Parents;
import com.main.sts.entities.RfidCards;
import com.main.sts.entities.Routes;
import com.main.sts.entities.Stops;
import com.main.sts.entities.Students;
import com.main.sts.entities.Transport;
import com.main.sts.entities.Trips;
import com.main.sts.messageworkers.Mail;
import com.main.sts.messageworkers.MailTempate;
import com.main.sts.messageworkers.MessagesThreadPool;
import com.main.sts.util.ImportColumns;
import com.main.sts.util.MakeObjects;
import com.main.sts.util.StudentsJspEntity;
import com.main.sts.util.SystemConstants;

@Service
public class StudentsService {

    private static final Logger logger = Logger.getLogger(StudentsService.class);

    private final static String subscriber_type = "student";
    private final static String alerts_type_sms = "sms";
    private final static String alerts_type_email = "email";

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private RfidCardsService cardsService;

    @Autowired
    private MakeObjects makeObjects;

    @Autowired
    private TripService tripService;

    @Autowired
    private BusesService busesService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private RfidCardsService rfidCardsService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StopsService stopsService;

    @Autowired
    private ImportColumns importColumns;

    @Autowired
    private AdminEmailService adminservice;

    @Autowired
    private MessagesThreadPool threadpool;

    @Autowired
    private MailTempate tempate;

    public String option;
    public List<RfidCards> cards = null;

    MailTempate mailtemplate = null;
    int i = 1, stud_id = 0, j = 0, k = 0;
    String filename = "";
    public String processFile(String filepath, String fname) {
        String msg = "";
        List<AdminEmails> emails = adminservice.getAllMails();
        try {
            filename = fname;
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            br.readLine();
            cards = rfidCardsService.getAvailableRfids("student", 0);

            while ((line = br.readLine()) != null) {
                option = "";
                if (cards.size() < i) {
                    logger.info("Rfids numbers are not availble to proceed");
                    for (AdminEmails a : emails) {
                        mailtemplate = tempate.mailTemplateForStudentImport(fname, k, j,
                                "Rfids numbers are not availble to proceed");
                        Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                        threadpool.addMessageToQueue(mail);
                    }
                    return "no_rfids";
                }
                String words[] = line.split(",");
                if (words.length > importColumns.getGr_number() && !words[importColumns.getGr_number()].isEmpty()) {

                    stud_id = processStudent(words, i);
                    int p = processFromHome(words, i, stud_id);
                    if (p == 0) {
                        deleteOnlyStudent(stud_id);
                        return "Import failed due to Invalid data at [" + i + "]row.Check Log for Details";
                    }
                    int q = processFromSchool(words, i, stud_id);
                    if (q == 0) {
                        deleteOnlyStudent(stud_id);
                        deleteTransport(stud_id, "student", SystemConstants.PICKUP);
                        return "Import failed due to Invalid data at [" + i + "]row.Check Log for Details";
                    }
                    processAddress(words, i, stud_id);
                    processParent(words, i, stud_id);
                    processEmailAlerts(words, i, stud_id);
                    processSmsAlerts(words, i, stud_id);
                    logger.info("*********Inserted [" + i + "] record***********");
                    msg = msg + "row [" + i + "] Inserted Successfully\n";
                    k++;

                }

                else {
                    logger.info("Student Gr_Number is Blank at row [" + i + "] so Skipped this row");
                    msg = msg + "Skipped row [" + i + "] as GR Number is Blank \n";
                    j++;
                }

                i++;
            }
            msg = msg + "\n";
            msg = msg + "Total [" + k + "] rows iserted \n";
            msg = msg + "Total [" + j + "] Rows Skipped or failed to Insert \n";
            for (AdminEmails a : emails) {
                mailtemplate = tempate.mailTemplateForStudentImport(fname, k, j, "None");
                Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                threadpool.addMessageToQueue(mail);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        i = 1;
        k = 0;
        j = 0;
        return msg;
    }

    private int processFromSchool(String[] words, int i, int stud_id) {
        Transport t = new Transport();
        List<AdminEmails> emails = adminservice.getAllMails();
        if (words.length > importColumns.getBus_from_school() && !words[importColumns.getBus_from_school()].isEmpty()) {

            Buses bus = busesService.getBusByLicence((words[importColumns.getBus_from_school()]));
            if (bus != null)
                t.setBus_id(bus.getBus_id());
            else {
                // t.setBus_id(0);
                logger.info("Bus Not found in DB!!!!!Skipping the Import now at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Bus Not found in DB!!!!!Skipping the Import now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
            }

        } else {
            t.setBus_id(0);
            logger.info("Bus licence number is blank,inserting 0 now at row [" + i + "]");
        }
        if (words.length > importColumns.getRoute_from_school()
                && !words[importColumns.getRoute_from_school()].isEmpty()) {

            Routes r = routeService.getRouteName(words[importColumns.getRoute_from_school()]);
            if (r != null)
                t.setRoute_id(r.getId());
            else {
                // t.setRoute_id(0);
                logger.info("Route name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Route name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
            }
        } else {
            t.setRoute_id(0);
            logger.info("Route Name is blank,inserting 0 now at row [" + i + "]");
        }

        if (words.length > importColumns.getDropoff_time() && !words[importColumns.getDropoff_time()].isEmpty()) {
            // TODO: this functionality is broken after creation of trip detail.
            Trips t1 = null;//tripService.getTripByName(words[importColumns.getDropoff_time()]);
            if (t1 != null) {
                t.setTrip_id(t1.getId());
                t.setBus_id(t1.getTripDetail().getBusid());
                t.setRoute_id(t1.getTripDetail().getRouteid());
                tripService.incrementTripSeat(t1.getId());

            } else {
                logger.info("Trip name not found in DB!!!!,Skipping Import at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Trip name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
                // t.setTrip_id(0);
            }
        }

        else {
            logger.info("Trip name is Blank,Inserting 0 at row [" + i + "]");
            t.setTrip_id(0);
        }

        if (words.length > importColumns.getStop_from_school() && !words[importColumns.getStop_from_school()].isEmpty()) {
            Stops s = stopsService.getStopName(words[importColumns.getStop_from_school()]);
            if (s != null)
                t.setStop_id(s.getId());
            else {
                logger.info("Stop name not found in DB,Inserting 0 at row [" + i + "]");
                t.setStop_id(0);
            }
        } else {
            logger.info("Stop name is Blank,Inserting 0 at row [" + i + "]");
            t.setStop_id(0);
        }

        t.setSubscriber_id(stud_id);
        t.setSubscriber_type("student");
        t.setTransport_type("Drop Off");
        if (option.equals("insert")) {
            studentsDao.insertTransport(t);
            logger.info("Transport from School inserted for row [" + i + "]");
        } else {
            Transport tp = transportService.getTransport(stud_id, subscriber_type, "Drop Off");
            t.setId(tp.getId());
            studentsDao.updateEntity(t);
            logger.info("Transport From School Updated for row [" + i + "]");
        }
        return 1;

    }

    private int processFromHome(String[] words, int i, int stud_id) {

        Transport t = new Transport();
        List<AdminEmails> emails = adminservice.getAllMails();
        if (words.length > importColumns.getBus_from_home() && !words[importColumns.getBus_from_home()].isEmpty()) {
            System.out.println(words[importColumns.getBus_from_home()]);
            Buses bus = busesService.getBusByLicence((words[importColumns.getBus_from_home()]));
            if (bus != null)
                t.setBus_id(bus.getBus_id());
            else {
                // t.setBus_id(0);
                logger.info("Bus Not found in DB!!!!,Skipping Import at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Bus name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
            }

        } else {
            t.setBus_id(0);
            logger.info("Bus licence number is blank,inserting 0 now at row [" + i + "]");
        }
        if (words.length > importColumns.getRoute_from_home() && !words[importColumns.getRoute_from_home()].isEmpty()) {

            Routes r = routeService.getRouteName(words[importColumns.getRoute_from_home()]);
            if (r != null)
                t.setRoute_id(r.getId());
            else {
                // t.setRoute_id(0);
                logger.info("Route name not found in DB!!!!,Skipping Import at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Route name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
            }
        } else {
            t.setRoute_id(0);
            logger.info("Route Name is blank,inserting 0 now at row [" + i + "]");
        }

        if (words.length > importColumns.getPickup_time() && !words[importColumns.getPickup_time()].isEmpty()) {
            // TODO: this functionality is broken after creation of trip detail.
            Trips t1 = null;//tripService.getTripByName(words[importColumns.getPickup_time()]);
            if (t1 != null) {
                t.setTrip_id(t1.getId());
                t.setBus_id(t1.getTripDetail().getBusid());
                t.setRoute_id(t1.getTripDetail().getRouteid());
                tripService.incrementTripSeat(t1.getId());
            } else {
                logger.info("Trip name not found in DB!!!!,Skipping Import at row [" + i + "]");
                for (AdminEmails a : emails) {
                    mailtemplate = tempate.mailTemplateForStudentImport(filename, k, j,
                            "Trip name not found in DB!!!,Skipping Import Now at row [" + i + "]");
                    Mail mail = new Mail(a.getEmail(), mailtemplate.getSubject(), mailtemplate.getMailBody());
                    threadpool.addMessageToQueue(mail);
                }
                return 0;
                // t.setTrip_id(0);
            }
        }

        else {
            logger.info("Trip name is Blank,Inserting 0 at row [" + i + "]");
            t.setTrip_id(0);
        }

        if (words.length > importColumns.getStop_from_home() && !words[importColumns.getStop_from_home()].isEmpty()) {
            Stops s = stopsService.getStopName(words[importColumns.getStop_from_home()]);
            if (s != null)
                t.setStop_id(s.getId());
            else {
                logger.info("Stop name not found in DB,Inserting 0 at row [" + i + "]");
                t.setStop_id(0);
            }
        } else {
            logger.info("Stop name is Blank,Inserting 0 at row [" + i + "]");
            t.setStop_id(0);
        }

        t.setSubscriber_id(stud_id);
        t.setSubscriber_type("student");
        t.setTransport_type("Pick Up");
        if (option.equals("insert")) {
            studentsDao.insertTransport(t);
            logger.info("Transport from Home inserted for row [" + i + "]");
        } else {
            Transport tp = transportService.getTransport(stud_id, subscriber_type, "Pick Up");
            t.setId(tp.getId());
            studentsDao.updateEntity(t);
            logger.info("Transport From Home Updated for row [" + i + "]");
        }
        return 1;
    }

    private void processSmsAlerts(String[] words, int i, int stud_id) {
        Alerts al = new Alerts();
        /*
         * if (words.length > 27) {
         * 
         * if (words[27].equalsIgnoreCase("on")) {
         */
        al.setAll_alerts("on");
        al.setNo_show("on");
        al.setLate("on");
        al.setRegularities("on");
        al.setIrregularities("on");

        /*
         * } else if (words[27].equalsIgnoreCase("off")) {
         * al.setAll_alerts("off"); al.setNo_show("off"); al.setLate("off");
         * al.setRegularities("off"); al.setIrregularities("off");
         * 
         * } else if (words[27].isEmpty()|| words[27] == "none") {
         * al.setAll_alerts("off"); if (words.length > 28) { if
         * (words[28].equalsIgnoreCase("on")) al.setNo_show("on"); else
         * al.setNo_show("off"); } else al.setNo_show("off");
         * 
         * if (words.length > 29) { if (words[29].equalsIgnoreCase("on"))
         * al.setLate("on"); else al.setLate("off"); } else al.setLate("off");
         * 
         * if (words.length > 30) { if (words[30].equalsIgnoreCase("on"))
         * al.setRegularities("on"); else al.setRegularities("off"); } else
         * al.setRegularities("off");
         * 
         * if (words.length > 31) { if (words[31].equalsIgnoreCase("on"))
         * al.setIrregularities("on"); else al.setIrregularities("off"); } else
         * al.setIrregularities("off"); } } else { al.setAll_alerts("off");
         * al.setNo_show("off"); al.setLate("off"); al.setRegularities("off");
         * al.setIrregularities("off"); }
         */
        al.setSubscriber_id(stud_id);
        al.setAlert_type(alerts_type_sms);
        al.setSubscriber_type("student");
        if (option.equals("insert")) {
            studentsDao.insertAlerts(al);
            logger.info("[ " + alerts_type_sms + " ] alerts inserted for row [" + i + "]");
        } else {
            Alerts e = getAlerts(stud_id, "student", alerts_type_sms);
            if (e != null) {
                al.setId(e.getId());
                studentsDao.updateEntity(al);
                logger.info("[ " + alerts_type_sms + " ] alerts Updated for row [" + i + "]");
            } else {
                studentsDao.insertAlerts(al);
                logger.info("[ " + alerts_type_sms + " ] alerts inserted*** for row [" + i + "]");
            }
        }

    }

    private void processEmailAlerts(String[] words, int i, int stud_id) {
        Alerts al = new Alerts();

        // if (words.length > 22) {

        // if (words[22].equalsIgnoreCase("on")) {
        al.setAll_alerts("on");
        al.setNo_show("on");
        al.setLate("on");
        al.setRegularities("on");
        al.setIrregularities("on");

        // } else if (words[22].equalsIgnoreCase("off")) {
        /*
         * al.setAll_alerts("off"); al.setNo_show("off"); al.setLate("off");
         * al.setRegularities("off"); al.setIrregularities("off");
         */

        // } else if (words[22].isEmpty() || words[22] == "none") {
        // al.setAll_alerts("off");

        /*
         * if (words.length > 23) { if (words[23].equalsIgnoreCase("on"))
         * al.setNo_show("on"); else al.setNo_show("off"); } else
         * al.setNo_show("off");
         * 
         * if (words.length > 24) { if (words[24].equalsIgnoreCase("on"))
         * al.setLate("on"); else al.setLate("off"); } else al.setLate("off");
         * 
         * if (words.length > 25) { if (words[25].equalsIgnoreCase("on"))
         * al.setRegularities("on"); else al.setRegularities("off"); } else
         * al.setRegularities("off");
         * 
         * if (words.length > 26) { if (words[26].equalsIgnoreCase("on"))
         * al.setIrregularities("on"); else al.setIrregularities("off"); } else
         * al.setIrregularities("off"); } } else { al.setAll_alerts("off");
         * al.setNo_show("off"); al.setLate("off"); al.setRegularities("off");
         * al.setIrregularities("off"); }
         */
        al.setSubscriber_id(stud_id);
        al.setAlert_type(alerts_type_email);
        al.setSubscriber_type("student");
        if (option.equals("insert")) {
            studentsDao.insertAlerts(al);
            logger.info("[ " + alerts_type_email + " ] alerts inserted for row [" + i + "]");
        } else {
            Alerts e = getAlerts(stud_id, "student", alerts_type_email);
            if (e != null) {
                al.setId(e.getId());
                studentsDao.updateEntity(al);
                logger.info("[ " + alerts_type_email + " ] alerts Updated for row [" + i + "]");
            } else {
                studentsDao.insertAlerts(al);
                logger.info("[ " + alerts_type_email + " ] alerts inserted*** for row [" + i + "]");
            }
        }
    }

    private void processAddress(String[] words, int i, int stud_id) {
        Address a = new Address();
        if (words.length > importColumns.getStreet() && !words[importColumns.getStreet()].isEmpty()) {

            a.setStreet(words[importColumns.getStreet()]);

        } else {
            logger.info("Street is blank, inserting None.Update later at row [" + i + "]");
            a.setStreet("none");
        }

        if (words.length > importColumns.getCity() && !words[importColumns.getCity()].isEmpty()) {

            a.setCity(words[importColumns.getCity()]);

        } else {
            logger.info("City is blank, inserting None.Update later at row [" + i + "]");
            a.setCity("none");
        }

        if (words.length > importColumns.getState() && !words[importColumns.getState()].isEmpty()) {

            a.setState(words[importColumns.getState()]);
        } else {
            logger.info("State is blank, inserting None.Update later at row [" + i + "]");
            a.setState("none");
        }

        if (words.length > importColumns.getPostal() && !words[importColumns.getPostal()].isEmpty()) {

            a.setPostal(words[importColumns.getPostal()]);
        } else {
            logger.info("Postal is blank, inserting None.Update later at row [" + i + "]");
            a.setPostal("none");
        }

        if (words.length > importColumns.getCountry() && !words[importColumns.getCountry()].isEmpty()) {

            a.setCountry(words[importColumns.getCountry()]);
        } else {
            logger.info("Country is blank, inserting None.Update later at row [" + i + "]");
            a.setCountry("none");
        }
        a.setSubscriber_id(stud_id);
        a.setSubscriber_type("student");
        if (option.equals("insert")) {
            studentsDao.insertAddress(a);
            logger.info("Address for row [" + i + "] inserted");
        } else {
            Address ad = getAddress(stud_id, "student");
            a.setId(ad.getId());
            studentsDao.updateEntity(a);
            logger.info("Address for row [" + i + "] Updated");
        }

    }

    private void processParent(String[] words, int i, int stud_id) {

        // Parents p=getParentByEmail(words[8]);
        // Parents p1=getParentByMobile(words[7]);
        Parents pt = new Parents();
        if (words.length > importColumns.getParent_email() && !words[importColumns.getParent_email()].isEmpty()) {

            pt.setEmail(words[importColumns.getParent_email()]);
        }

        else {
            logger.info("Parent Email kept blank .Inserting None now,Please Update later at row [" + i + "]");
            pt.setEmail("none");

        }

        if (words.length > importColumns.getParent_mobile() && !words[importColumns.getParent_mobile()].isEmpty()) {

            pt.setMobile(words[importColumns.getParent_mobile()]);

        } else {
            logger.info("Parent Mobile kept blank.Inserting 0 now,Please Update later at row [" + i + "]");
            pt.setMobile("0");
        }

        if (words.length > importColumns.getParent_first_name()
                && !words[importColumns.getParent_first_name()].isEmpty()) {

            pt.setFirst_name(words[importColumns.getParent_first_name()]);
        } else {
            pt.setFirst_name("None");
            logger.info("Parent First Name is Blank so Isnerted None now. Update later at row [" + i + "]");
        }

        if (words.length > importColumns.getParent_last_name() && !words[importColumns.getParent_last_name()].isEmpty()) {

            pt.setLast_name(words[importColumns.getParent_last_name()]);
        } else {
            pt.setLast_name("None");
            logger.info("Parent Last Name is Blank so Isnerted None now. Update later at row [" + i + "]");
        }

        pt.setStudent_id(stud_id);
        if (option.equals("insert")) {
            studentsDao.insertParent(pt);
            logger.info("Parent Inserted for row [" + i + "]");
        } else {
            Parents p = getParent(stud_id);
            pt.setId(p.getId());
            studentsDao.updateEntity(pt);
            logger.info("Parent Updated for row [" + i + "]");
        }

    }

    private Parents getParentByMobile(String string) {

        return studentsDao.getparentByMobile(string);
    }

    private Parents getParentByEmail(String string) {
        return studentsDao.getparentByEmail(string);

    }

    public int processStudent(String words[], int line_no) {
        int stud_id = 0;
        Students st = new Students();
        Students stud = getStudentByGrNumber(words[importColumns.getGr_number()]);

        st.setFirst_name(words[importColumns.getFirst_name()]);
        st.setLast_name(words[importColumns.getLast_name()]);
        st.setGr_number(words[importColumns.getGr_number()]);
        st.setGender(words[importColumns.getGender()]);
        st.setRfid_number(cards.get(line_no - 1).getRfid_number());
        st.setStudent_grade(words[importColumns.getStudent_grade()]);
        if (stud != null) {
            logger.info("Gr Number Allready exist so Updating student record for [" + line_no + "]row ");
            st.setId(stud.getId());;
            studentsDao.updateEntity(st);
            logger.info("Student [ " + st.getFirst_name() + " ] Record Updated for row [" + line_no + "]");
            stud_id = st.getId();
            option = "update";
        }

        else {

            insert(st);
            logger.info("Student [ " + st.getFirst_name() + " ] Record Inserted for row [" + line_no + "]");
            stud_id = st.getId();
            option = "insert";
        }

        return stud_id;

    }

    private Students getStudentRfid(String string) {

        return studentsDao.getStudentRfid(string);
    }

    public List<StudentsJspEntity> getAllStudents() {
        return studentsDao.getStudentsDetails();
    }

    public List<Students> getStudentsList() {
        return studentsDao.getAllStudents();
    }

    public void addStudent(HttpServletRequest request) {

        Students student = makeObjects.getStudent(request);
        studentsDao.insertStudent(student);
        cardsService.updateRfidWhenAllocated(student.getId(), student.getFirst_name() + " " + student.getLast_name(),
                student.getRfid_number());

        Parents parent = makeObjects.getParent(request, student.getId());
        studentsDao.insertParent(parent);

        Address address = makeObjects.getAddress(request, student.getId(), subscriber_type);
        studentsDao.insertAddress(address);

        Alerts smsAlerts = makeObjects.getAlerts(request, student.getId(), subscriber_type, alerts_type_sms);
        Alerts emailAlerts = makeObjects.getAlerts(request, student.getId(), subscriber_type, alerts_type_email);
        studentsDao.insertAlerts(smsAlerts);
        studentsDao.insertAlerts(emailAlerts);

        Transport transportFromHome = makeObjects.getTransport(request, student.getId(), subscriber_type,
                SystemConstants.PICKUP);
        Transport transportFromSchool = makeObjects.getTransport(request, student.getId(), subscriber_type,
                SystemConstants.DROPOFF);
        studentsDao.insertTransport(transportFromSchool);
        studentsDao.insertTransport(transportFromHome);

        if (request.getParameter("trip_id_fromhome").length() != 0) {
            int trip_id_from_home = Integer.parseInt(request.getParameter("trip_id_fromhome"));
            if (busSeatsAvailable(trip_id_from_home)) {
                tripService.incrementTripSeat(transportFromHome.getTrip_id());

            }

        }
        if (request.getParameter("trip_id_fromschool").length() != 0) {
            int trip_id_from_school = Integer.parseInt(request.getParameter("trip_id_fromschool"));

            if (busSeatsAvailable(trip_id_from_school)) {

                tripService.incrementTripSeat(transportFromSchool.getTrip_id());

            }

        }

    }

    public List<StudentsJspEntity> getAllStudentsForJsp() {
        return studentsDao.getStudentsDetails();
    }
    public List<StudentsJspEntity> getAllParentStudentsForJsp(String fullname) {
        return studentsDao.getParentStudentsDetails(fullname);
    }
    public boolean busSeatsAvailable(int trip_id) {

        Trips trip = tripService.getTrip(trip_id);
        int seats_filled = trip.getSeats_filled();
        Buses buses = busesService.getBusById(trip.getTripDetail().getBusid());
        int total_seats = Integer.parseInt(buses.getBus_capacity());
        if (seats_filled == total_seats) {
            return false;
        } else {
            return true;
        }
    }

    public Students getStudent(int id) {

        return studentsDao.getStudent(id);
    }

    public Parents getParent(int student_id) {

        return studentsDao.getParent(student_id);
    }

    public Alerts getAlerts(int subscriber_id, String subscriber_type, String alert_type) {
        return studentsDao.getAlerts(subscriber_id, subscriber_type, alert_type);
    }

    public Address getAddress(int subscriber_id, String subscriber_type) {
        return studentsDao.getAddress(subscriber_id, subscriber_type);
    }

    public void updateStudent(HttpServletRequest request) {
        String new_rfid = request.getParameter("rfid_number");
        Students st = studentsDao.getStudent(Integer.parseInt(request.getParameter("id")));
        String rfid_num = null;
        if (new_rfid.length() > 6) {

            // System.out.println("new==>" + new_rfid);
            cardsService.updateRfidWhenDeallocated(st.getRfid_number());
            cardsService.updateRfidWhenAllocated(st.getId(), st.getFirst_name() + " " + st.getLast_name(), new_rfid);
            rfid_num = new_rfid;
        } else {
            rfid_num = st.getRfid_number();
        }
        Students student = makeObjects.getStudent(request);
        student.setId(st.getId());
        student.setRfid_number(rfid_num);
        // System.out.println(student);
        studentsDao.updateEntity(student);

        Parents parent = makeObjects.getParent(request, student.getId());
        parent.setId(getParent(student.getId()).getId());
        studentsDao.updateEntity(parent);
        Transport transport = transportService.getTransport(st.getId(), "student", SystemConstants.PICKUP);
        Transport homeTransport = makeObjects.getTransport(request, st.getId(), "student", SystemConstants.PICKUP);
        homeTransport.setId(transport.getId());
        if (request.getParameter("bus_id_number_fromhome").length() != 0) {

            System.out.println(homeTransport);
            studentsDao.updateEntity(homeTransport);
        }

        Transport transport1 = transportService.getTransport(st.getId(), "student", SystemConstants.DROPOFF);
        Transport schoolTransport = makeObjects.getTransport(request, st.getId(), "student", SystemConstants.DROPOFF);
        schoolTransport.setId(transport1.getId());
        if (request.getParameter("bus_id_number_fromschool").length() != 0) {
            studentsDao.updateEntity(schoolTransport);

        }

        Address add = getAddress(st.getId(), "student");
        // System.out.println(add);
        Address address = makeObjects.getAddress(request, st.getId(), "student");
        address.setId(add.getId());
        studentsDao.updateEntity(address);

        Alerts email_al = getAlerts(st.getId(), "student", "email");
        Alerts sms_al = getAlerts(st.getId(), "student", "sms");

        Alerts emailAlerts = makeObjects.getAlerts(request, st.getId(), "student", "email");
        Alerts smsAlerts = makeObjects.getAlerts(request, st.getId(), "student", "sms");

        emailAlerts.setId(email_al.getId());
        smsAlerts.setId(sms_al.getId());
        studentsDao.updateEntity(emailAlerts);
        studentsDao.updateEntity(smsAlerts);
        if (request.getParameter("trip_id_fromhome").length() != 0) {
            int trip_id_from_home = Integer.parseInt(request.getParameter("trip_id_fromhome"));
            if (busSeatsAvailable(trip_id_from_home)) {
                tripService.incrementTripSeat(homeTransport.getTrip_id());

            }

        }
        if (request.getParameter("trip_id_fromschool").length() != 0) {
            int trip_id_from_school = Integer.parseInt(request.getParameter("trip_id_fromschool"));

            if (busSeatsAvailable(trip_id_from_school)) {

                tripService.incrementTripSeat(schoolTransport.getTrip_id());

            }

        }
        if (request.getParameter("trip_id_fromhome").length() != 0)
            if (transport.getTrip_id() != homeTransport.getTrip_id())
                tripService.decrementTripSeat(transport.getTrip_id());
        if (request.getParameter("trip_id_fromschool").length() != 0) {
            if (transport1.getTrip_id() != schoolTransport.getTrip_id()) {

                tripService.decrementTripSeat(transport1.getTrip_id());
            }
        }

        logger.info("Student [ " + student.getFirst_name() + " " + student.getLast_name() + " ] has been updated");

    }

    public void deleteStudent(int student_id) {

        Students student = studentsDao.getStudent(student_id);
        cardsService.updateRfidWhenDeallocated(student.getRfid_number());
        studentsDao.deleteEntity(student);
        Parents parent = getParent(student_id);
        studentsDao.deleteEntity(parent);
        Address address = getAddress(student_id, subscriber_type);
        studentsDao.deleteEntity(address);
        Transport homeTransport = transportService.getTransport(student.getId(), "student", SystemConstants.PICKUP);
        if (homeTransport.getTrip_id() != 0) {
            tripService.decrementTripSeat(homeTransport.getTrip_id());
            studentsDao.deleteEntity(homeTransport);

        }
        Transport schoolTransport = transportService.getTransport(student.getId(), "student", SystemConstants.DROPOFF);
        if (schoolTransport.getTrip_id() != 0) {
            tripService.decrementTripSeat(schoolTransport.getTrip_id());
            // System.out.println(schoolTransport.getTrip_id());
            studentsDao.deleteEntity(schoolTransport);

        }
        Alerts email_al = getAlerts(student.getId(), "student", "email");
        Alerts sms_al = getAlerts(student.getId(), "student", "sms");
        studentsDao.deleteEntity(sms_al);
        studentsDao.deleteEntity(email_al);
        logger.info("Student [ " + student.getFirst_name() + " " + student.getLast_name() + " ] has been deleted");
    }

    public Students getStudentByName(String string, String string2) {

        return studentsDao.getStudentByName(string, string2);
    }

    public void insert(Students st) {
        studentsDao.insertStudent(st);

    }

    public Students getStudentByGrNumber(String string) {

        return studentsDao.getStudentByGrNumber(string);
    }

    public List<Students> searchStudents(String str, String searchOption) {

        return studentsDao.search(str, searchOption);
    }
    public void deleteOnlyStudent(int student_id) {
        Students student = studentsDao.getStudent(student_id);
        cardsService.updateRfidWhenDeallocated(student.getRfid_number());
        studentsDao.deleteEntity(student);

    }
    public void deleteTransport(int student_id, String type, String trip_type) {
        Transport transport = transportService.getTransport(student_id, type, trip_type);
        if (transport.getTrip_id() != 0)
            tripService.decrementTripSeat(transport.getTrip_id());
        studentsDao.deleteEntity(transport);
    }
}
