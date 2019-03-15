--
-- easycommuteQL database dump
--

-- Dumped from database version 9.3.7
-- Dumped by pg_dump version 9.4.0
-- Started on 2015-10-05 16:56:52 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 223 (class 3079 OID 11787)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2286 (class 0 OID 0)
-- Dependencies: 223
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 236 (class 1255 OID 64514)
-- Name: getstudents(); Type: FUNCTION; Schema: public; Owner: easycommute
--

CREATE FUNCTION getstudents() RETURNS TABLE(first_name character varying, last_name character varying, gr_number character varying, rfid_number character varying, bus_licence_number_home character varying, bus_licence_number_school character varying, stop_name_home character varying, stop_name_school character varying)
    LANGUAGE sql
    AS $$
	

	 SELECT s.first_name as first_name ,s.last_name as last_name,s.gr_number as gr_number,s.rfid_number as rfid_number,
	 b.bus_licence_number as bus_licence_number_home,b1.bus_licence_number as bus_licence_number_school,
	 st.stop_name as stop_name_home ,st1.stop_name as stop_name_school
    from students as s
    INNER JOIN transport as t on s.id = t.subscriber_id and t.transport_type='from_home'
    INNER JOIN transport as t1 on s.id = t1.subscriber_id and t1.transport_type='from_school'
    INNER JOIN buses as b on t.bus_id =b.bus_id
    INNER JOIN buses as b1 on t1.bus_id =b1.bus_id
    INNER JOIN stops as st on t.stop_id =st.id
    INNER JOIN stops as st1 on t1.stop_id =st1.id;
	
	
$$;


ALTER FUNCTION public.getstudents() OWNER TO easycommute;

--
-- TOC entry 237 (class 1255 OID 64515)
-- Name: getstudentsforjsp1234(); Type: FUNCTION; Schema: public; Owner: easycommute
--

CREATE FUNCTION getstudentsforjsp1234() RETURNS TABLE(first_name character varying, last_name character varying, gr_number character varying, rfid_number character varying, bus_licence_number_home character varying, bus_licence_number_school character varying, stop_name_home character varying, stop_name_school character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE 
	user_property_id int;
BEGIN  
	  
	

	 SELECT s.first_name as first_name ,s.last_name as last_name,s.gr_number as gr_number,s.rfid_number as rfid_number,
	 b.bus_licence_number as bus_licence_number_home,b1.bus_licence_number as bus_licence_number_school,
	 st.stop_name as stop_name_home ,st1.stop_name as stop_name_school
    from students as s
    LEFT JOIN transport as t on s.id = t.subscriber_id and t.transport_type='from_home'
    LEFT JOIN transport as t1 on s.id = t1.subscriber_id and t1.transport_type='from_school'
    LEFT JOIN buses as b on t.bus_id =b.bus_id
    LEFT JOIN buses as b1 on t1.bus_id =b1.bus_id
    LEFT JOIN stops as st on t.stop_id =st.id
    LEFT JOIN stops as st1 on t1.stop_id =st1.id;
	
END;	
$$;


ALTER FUNCTION public.getstudentsforjsp1234() OWNER TO easycommute;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 64516)
-- Name: address; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE address (
    id integer NOT NULL,
    subscriber_type character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    postal character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    subscriber_id integer
);


ALTER TABLE address OWNER TO easycommute;

--
-- TOC entry 171 (class 1259 OID 64522)
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE address_id_seq OWNER TO easycommute;

--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 171
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE address_id_seq OWNED BY address.id;


--
-- TOC entry 172 (class 1259 OID 64524)
-- Name: admin_emails; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE admin_emails (
    id integer NOT NULL,
    email character varying(255)
);


ALTER TABLE admin_emails OWNER TO easycommute;

--
-- TOC entry 173 (class 1259 OID 64527)
-- Name: alerts; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE alerts (
    id integer NOT NULL,
    subscriber_type character varying(255) NOT NULL,
    alert_type character varying(255) NOT NULL,
    all_alerts character varying(255) NOT NULL,
    no_show character varying(255) NOT NULL,
    late character varying(255) NOT NULL,
    irregularities character varying(255) NOT NULL,
    regularities character varying(255) NOT NULL,
    subscriber_id integer
);


ALTER TABLE alerts OWNER TO easycommute;

--
-- TOC entry 174 (class 1259 OID 64533)
-- Name: alerts_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE alerts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE alerts_id_seq OWNER TO easycommute;

--
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 174
-- Name: alerts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE alerts_id_seq OWNED BY alerts.id;


--
-- TOC entry 214 (class 1259 OID 97149)
-- Name: attendance; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE attendance (
    id integer NOT NULL,
    date character varying(255),
    in_time character varying(255),
    out_time character varying(255),
    student_id integer NOT NULL,
    reason character varying(255),
    session_id integer NOT NULL
);


ALTER TABLE attendance OWNER TO easycommute;

--
-- TOC entry 219 (class 1259 OID 97437)
-- Name: bus_gps_data; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE bus_gps_data (
    id integer NOT NULL,
    bus_id character varying(255),
    lat character varying(255),
    long character varying(255),
    trip_id character varying(255)
);


ALTER TABLE bus_gps_data OWNER TO easycommute;

--
-- TOC entry 220 (class 1259 OID 97445)
-- Name: buses; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE buses (
    bus_id bigint NOT NULL,
    bus_allotted character varying(255),
    bus_capacity character varying(255),
    bus_licence_number character varying(255),
    bus_make_model character varying(255),
    driverid integer NOT NULL
);


ALTER TABLE buses OWNER TO easycommute;

--
-- TOC entry 175 (class 1259 OID 64541)
-- Name: daily_bus_coordinates; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_bus_coordinates (
    id integer NOT NULL,
    bus_speed integer NOT NULL,
    date character varying(255),
    latitude double precision,
    longitude double precision,
    "time" character varying(255),
    trip_id integer NOT NULL
);


ALTER TABLE daily_bus_coordinates OWNER TO easycommute;

--
-- TOC entry 176 (class 1259 OID 64547)
-- Name: daily_bus_notification; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_bus_notification (
    id integer NOT NULL,
    bus_id integer NOT NULL,
    bus_licence_number character varying(255),
    date character varying(255),
    message_type character varying(255),
    notification character varying(255),
    status character varying(255),
    "time" character varying(255),
    trip_id integer NOT NULL
);


ALTER TABLE daily_bus_notification OWNER TO easycommute;

--
-- TOC entry 177 (class 1259 OID 64553)
-- Name: daily_bus_stops; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_bus_stops (
    id integer NOT NULL,
    arrived_time character varying(255),
    date character varying(255),
    expected_time character varying(255),
    is_eta_sent boolean NOT NULL,
    is_stop_out_of_range boolean NOT NULL,
    latitude double precision,
    longitude double precision,
    routestop_id integer NOT NULL,
    trip_id integer NOT NULL
);


ALTER TABLE daily_bus_stops OWNER TO easycommute;

--
-- TOC entry 178 (class 1259 OID 64559)
-- Name: daily_driver_data; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_driver_data (
    id integer NOT NULL,
    date character varying(255),
    in_driver_id integer NOT NULL,
    in_latitude double precision,
    in_longitude double precision,
    in_time character varying(255),
    is_session_closed_by_system boolean NOT NULL,
    out_driver_id integer NOT NULL,
    out_latitude double precision,
    out_longitude double precision,
    out_time character varying(255),
    trip_id integer NOT NULL,
    in_driver_name character varying,
    out_driver_name character varying
);


ALTER TABLE daily_driver_data OWNER TO easycommute;

--
-- TOC entry 179 (class 1259 OID 64565)
-- Name: daily_driver_speed; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_driver_speed (
    id integer NOT NULL,
    bus_licence_number character varying(255),
    date character varying(255),
    driver_id integer NOT NULL,
    driver_name character varying(255),
    end_latitude double precision,
    end_longitude double precision,
    end_time character varying(255),
    highest_speed integer NOT NULL,
    start_longitude double precision,
    start_time character varying(255),
    stat_latitude double precision,
    trip_name character varying(255)
);


ALTER TABLE daily_driver_speed OWNER TO easycommute;

--
-- TOC entry 180 (class 1259 OID 64571)
-- Name: daily_running_busses; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_running_busses (
    id integer NOT NULL,
    arrived_time character varying(255),
    bus_status character varying(255),
    current_stop character varying(255),
    date character varying(255),
    driver_id integer NOT NULL,
    is_bus_arrived_to_school boolean NOT NULL,
    is_bus_out_of_school boolean NOT NULL,
    is_message_sent_to_first_stop boolean NOT NULL,
    latitude character varying(255),
    longitude character varying(255),
    students_in_bus integer NOT NULL,
    trip_end_time character varying(255),
    trip_id integer NOT NULL,
    trip_start_time character varying(255),
    trip_status character varying(255)
);


ALTER TABLE daily_running_busses OWNER TO easycommute;

--
-- TOC entry 181 (class 1259 OID 64577)
-- Name: daily_subscriber_data; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE daily_subscriber_data (
    id integer NOT NULL,
    date character varying(255),
    in_latitude double precision,
    in_longitude double precision,
    in_stop character varying(255),
    in_time character varying(255),
    out_latitude double precision,
    out_longitude double precision,
    out_stop character varying(255),
    out_time character varying(255),
    subscriber_boarded_wrong_bus character varying(255),
    subscriber_boarded_wrong_stop_inbound character varying(255),
    subscriber_boarded_wrong_stop_outbound character varying(255),
    subscriber_boarded_wrong_trip character varying(255),
    subscriber_id integer NOT NULL,
    subscriber_name character varying(255),
    subscriber_type character varying(255),
    trip_id integer NOT NULL
);


ALTER TABLE daily_subscriber_data OWNER TO easycommute;

--
-- TOC entry 182 (class 1259 OID 64583)
-- Name: dailyrunningbusesjspentity; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE dailyrunningbusesjspentity (
    id integer NOT NULL,
    arrived_time character varying(255),
    bus_id integer NOT NULL,
    bus_licence_number character varying(255),
    bus_status character varying(255),
    current_stop character varying(255),
    date character varying(255),
    driver_id integer NOT NULL,
    driver_name character varying(255),
    driver_speed_id character varying(255),
    latitude character varying(255),
    longitude character varying(255),
    students_in_bus integer NOT NULL,
    trip_id integer NOT NULL
);


ALTER TABLE dailyrunningbusesjspentity OWNER TO easycommute;

--
-- TOC entry 183 (class 1259 OID 64589)
-- Name: drivers; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE drivers (
    id integer NOT NULL,
    driver_id character varying NOT NULL,
    rfid_number character varying,
    driver_name character varying,
    contact_number character varying,
    available integer,
    active integer,
    street character varying,
    city character varying,
    state character varying,
    zip character varying,
    country character varying
);


ALTER TABLE drivers OWNER TO easycommute;

--
-- TOC entry 184 (class 1259 OID 64595)
-- Name: drivers_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE drivers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE drivers_id_seq OWNER TO easycommute;

--
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 184
-- Name: drivers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE drivers_id_seq OWNED BY drivers.id;


--
-- TOC entry 185 (class 1259 OID 64597)
-- Name: eta_process; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE eta_process (
    id integer NOT NULL,
    actual_time character varying(255),
    bus_id integer NOT NULL,
    bus_licence_number character varying(255),
    count integer NOT NULL,
    date character varying(255),
    expected_time character varying(255),
    is_eta_sent boolean NOT NULL,
    route_id integer NOT NULL,
    stop_id integer NOT NULL,
    stop_name character varying(255),
    stop_number integer NOT NULL,
    trip_id integer NOT NULL,
    trip_type character varying(255)
);


ALTER TABLE eta_process OWNER TO easycommute;

--
-- TOC entry 218 (class 1259 OID 97322)
-- Name: gps_data; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE gps_data (
    id integer NOT NULL,
    busid character varying(255),
    gpslat character varying(255),
    gpslong character varying(255),
    tripid character varying(255)
);


ALTER TABLE gps_data OWNER TO easycommute;

--
-- TOC entry 186 (class 1259 OID 64603)
-- Name: guardians; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE guardians (
    id integer NOT NULL,
    email character varying(255),
    email_alert_id integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    mobile_number character varying(255),
    relation character varying(255),
    sms_alert_id integer NOT NULL,
    student_id integer NOT NULL
);


ALTER TABLE guardians OWNER TO easycommute;

--
-- TOC entry 222 (class 1259 OID 97461)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO easycommute;

--
-- TOC entry 187 (class 1259 OID 64611)
-- Name: parents; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE parents (
    id integer NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    student_id integer,
    mobile character varying
);


ALTER TABLE parents OWNER TO easycommute;

--
-- TOC entry 188 (class 1259 OID 64617)
-- Name: parents_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE parents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE parents_id_seq OWNER TO easycommute;

--
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 188
-- Name: parents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE parents_id_seq OWNED BY parents.id;


--
-- TOC entry 189 (class 1259 OID 64619)
-- Name: plexus_mediator_record; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE plexus_mediator_record (
    id integer NOT NULL,
    date character varying(255),
    device_type character varying(255),
    message character varying(255),
    mobile_number character varying(255),
    notification_id character varying(255),
    person_name character varying(255),
    status character varying(255),
    student_id integer NOT NULL,
    "time" character varying(255),
    trip_name character varying(255),
    trip_type character varying(255)
);


ALTER TABLE plexus_mediator_record OWNER TO easycommute;

--
-- TOC entry 217 (class 1259 OID 97167)
-- Name: price_fare; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE price_fare (
    fare_id integer NOT NULL,
    source_stop_id integer,
    dest_stop_id integer,
    fare integer,
    auto_fare integer,
    bus_fare integer
);


ALTER TABLE price_fare OWNER TO easycommute;

--
-- TOC entry 216 (class 1259 OID 97165)
-- Name: price_fare_fare_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE price_fare_fare_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE price_fare_fare_id_seq OWNER TO easycommute;

--
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 216
-- Name: price_fare_fare_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE price_fare_fare_id_seq OWNED BY price_fare.fare_id;


--
-- TOC entry 190 (class 1259 OID 64625)
-- Name: rfid_cards; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE rfid_cards (
    id integer NOT NULL,
    rfid_number character varying,
    type character varying,
    available integer,
    allocated_to integer,
    allocated_time character varying,
    allocated_person_name character varying,
    active integer
);


ALTER TABLE rfid_cards OWNER TO easycommute;

--
-- TOC entry 191 (class 1259 OID 64631)
-- Name: rfid_cards_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE rfid_cards_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rfid_cards_id_seq OWNER TO easycommute;

--
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 191
-- Name: rfid_cards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE rfid_cards_id_seq OWNED BY rfid_cards.id;


--
-- TOC entry 192 (class 1259 OID 64633)
-- Name: roles; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE roles (
    role_id integer NOT NULL,
    role_name character varying NOT NULL
);


ALTER TABLE roles OWNER TO easycommute;

--
-- TOC entry 193 (class 1259 OID 64639)
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE roles_role_id_seq OWNER TO easycommute;

--
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 193
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE roles_role_id_seq OWNED BY roles.role_id;


--
-- TOC entry 194 (class 1259 OID 64641)
-- Name: route_stops_list; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE route_stops_list (
    id integer NOT NULL,
    stop_number integer NOT NULL,
    stop_time character varying NOT NULL,
    route_id integer NOT NULL,
    stop_id integer NOT NULL
);


ALTER TABLE route_stops_list OWNER TO easycommute;

--
-- TOC entry 195 (class 1259 OID 64647)
-- Name: route_stops_list_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE route_stops_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE route_stops_list_id_seq OWNER TO easycommute;

--
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 195
-- Name: route_stops_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE route_stops_list_id_seq OWNED BY route_stops_list.id;


--
-- TOC entry 196 (class 1259 OID 64649)
-- Name: routes; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE routes (
    id integer NOT NULL,
    isassigned character varying(255),
    route_name character varying(255),
    route_type character varying(255)
);


ALTER TABLE routes OWNER TO easycommute;

--
-- TOC entry 197 (class 1259 OID 64655)
-- Name: routes_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE routes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE routes_id_seq OWNER TO easycommute;

--
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 197
-- Name: routes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE routes_id_seq OWNED BY routes.id;


--
-- TOC entry 215 (class 1259 OID 97157)
-- Name: session; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE session (
    id integer NOT NULL,
    end_time character varying(255),
    session_name character varying(255),
    session_type character varying(255),
    starting_time character varying(255),
    end_time_hours integer NOT NULL,
    end_time_minutes integer NOT NULL,
    starting_time_hours integer NOT NULL,
    starting_time_minutes integer NOT NULL
);


ALTER TABLE session OWNER TO easycommute;

--
-- TOC entry 198 (class 1259 OID 64657)
-- Name: settings; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE settings (
    id integer NOT NULL,
    from_email character varying(255),
    school_latitude double precision,
    school_longitude double precision,
    smtp_password character varying(255),
    smtp_port character varying(255),
    smtp_server_host character varying(255),
    phone_number_alerts character varying(255),
    school_name character varying(255)
);


ALTER TABLE settings OWNER TO easycommute;

--
-- TOC entry 199 (class 1259 OID 64663)
-- Name: staff; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE staff (
    id integer NOT NULL,
    email character varying(255),
    full_name character varying(255),
    gender character varying(255),
    mobile_number character varying(255),
    rfid_number character varying(255),
    staff_id character varying(255)
);


ALTER TABLE staff OWNER TO easycommute;

--
-- TOC entry 200 (class 1259 OID 64669)
-- Name: staffjspentity; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE staffjspentity (
    id integer NOT NULL,
    full_name character varying(255),
    mobile_number character varying(255),
    rfid_number character varying(255),
    staff_id character varying(255),
    bus_licence_number_home character varying(255),
    bus_licence_number_school character varying(255),
    stop_name_home character varying(255),
    stop_name_school character varying(255)
);


ALTER TABLE staffjspentity OWNER TO easycommute;

--
-- TOC entry 201 (class 1259 OID 64675)
-- Name: stops; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE stops (
    id integer NOT NULL,
    geofence character varying(255),
    latitude character varying(255),
    longitude character varying(255),
    stop_name character varying(255),
    isassigned character varying(255)
);


ALTER TABLE stops OWNER TO easycommute;

--
-- TOC entry 202 (class 1259 OID 64681)
-- Name: stops_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE stops_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stops_id_seq OWNER TO easycommute;

--
-- TOC entry 2296 (class 0 OID 0)
-- Dependencies: 202
-- Name: stops_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE stops_id_seq OWNED BY stops.id;


--
-- TOC entry 203 (class 1259 OID 64683)
-- Name: students; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE students (
    id integer NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    gr_number character varying(255),
    gender character varying(255),
    rfid_number character varying(255),
    grade character varying(255),
    student_grade character varying(255)
);


ALTER TABLE students OWNER TO easycommute;

--
-- TOC entry 204 (class 1259 OID 64689)
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_id_seq OWNER TO easycommute;

--
-- TOC entry 2297 (class 0 OID 0)
-- Dependencies: 204
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- TOC entry 205 (class 1259 OID 64691)
-- Name: studentsjspentity; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE studentsjspentity (
    id integer NOT NULL,
    bus_licence_number_home character varying(255),
    bus_licence_number_school character varying(255),
    first_name character varying(255),
    gr_number character varying(255),
    last_name character varying(255),
    rfid_number character varying(255),
    stop_name_home character varying(255),
    stop_name_school character varying(255),
    bus_licence_number character varying(255),
    stop_name character varying(255)
);


ALTER TABLE studentsjspentity OWNER TO easycommute;

--
-- TOC entry 206 (class 1259 OID 64697)
-- Name: temp; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE temp (
    "time" character varying
);


ALTER TABLE temp OWNER TO easycommute;

--
-- TOC entry 207 (class 1259 OID 64703)
-- Name: transport; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE transport (
    id integer NOT NULL,
    subscriber_type character varying(255) NOT NULL,
    subscriber_id integer,
    bus_id integer NOT NULL,
    route_id integer NOT NULL,
    trip_id integer NOT NULL,
    stop_id integer NOT NULL,
    transport_type character varying(255)
);


ALTER TABLE transport OWNER TO easycommute;

--
-- TOC entry 208 (class 1259 OID 64709)
-- Name: transport_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE transport_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE transport_id_seq OWNER TO easycommute;

--
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 208
-- Name: transport_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE transport_id_seq OWNED BY transport.id;


--
-- TOC entry 213 (class 1259 OID 88957)
-- Name: trip_history; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE trip_history (
    id integer NOT NULL,
    date character varying(255),
    report_status character varying(255),
    students_absent character varying(255),
    students_present character varying(255),
    trip_name character varying(255),
    trip_type character varying(255),
    bus_licence_number character varying(255)
);


ALTER TABLE trip_history OWNER TO easycommute;

--
-- TOC entry 209 (class 1259 OID 64711)
-- Name: trips; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE trips (
    id integer NOT NULL,
    busid integer NOT NULL,
    routeid integer NOT NULL,
    school_time_hours integer NOT NULL,
    school_time_minutes integer NOT NULL,
    seats_filled integer NOT NULL,
    trip_end_time_hours integer NOT NULL,
    trip_end_time_minutes integer NOT NULL,
    trip_name character varying(255),
    trip_start_time_hours integer NOT NULL,
    trip_start_time_minutes integer NOT NULL,
    trip_type character varying(255)
);


ALTER TABLE trips OWNER TO easycommute;

--
-- TOC entry 221 (class 1259 OID 97453)
-- Name: user_gps_data; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE user_gps_data (
    id integer NOT NULL,
    booking_id character varying(255),
    lat character varying(255),
    long character varying(255),
    user_id character varying(255)
);


ALTER TABLE user_gps_data OWNER TO easycommute;

--
-- TOC entry 210 (class 1259 OID 64717)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE user_roles (
    user_id integer,
    role_id integer
);


ALTER TABLE user_roles OWNER TO easycommute;

--
-- TOC entry 211 (class 1259 OID 64720)
-- Name: users; Type: TABLE; Schema: public; Owner: easycommute; Tablespace: 
--

CREATE TABLE users (
    user_id integer NOT NULL,
    full_name character varying NOT NULL,
    user_name character varying NOT NULL,
    password character varying NOT NULL,
    access_status character varying,
    name character varying(255)
);


ALTER TABLE users OWNER TO easycommute;

--
-- TOC entry 212 (class 1259 OID 64726)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: easycommute
--

CREATE SEQUENCE users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_user_id_seq OWNER TO easycommute;

--
-- TOC entry 2299 (class 0 OID 0)
-- Dependencies: 212
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: easycommute
--

ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;


--
-- TOC entry 2075 (class 2604 OID 64728)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY address ALTER COLUMN id SET DEFAULT nextval('address_id_seq'::regclass);


--
-- TOC entry 2076 (class 2604 OID 64729)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY alerts ALTER COLUMN id SET DEFAULT nextval('alerts_id_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 64730)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY drivers ALTER COLUMN id SET DEFAULT nextval('drivers_id_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 64731)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY parents ALTER COLUMN id SET DEFAULT nextval('parents_id_seq'::regclass);


--
-- TOC entry 2087 (class 2604 OID 97170)
-- Name: fare_id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY price_fare ALTER COLUMN fare_id SET DEFAULT nextval('price_fare_fare_id_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 64732)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY rfid_cards ALTER COLUMN id SET DEFAULT nextval('rfid_cards_id_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 64733)
-- Name: role_id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY roles ALTER COLUMN role_id SET DEFAULT nextval('roles_role_id_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 64734)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY route_stops_list ALTER COLUMN id SET DEFAULT nextval('route_stops_list_id_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 64735)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY routes ALTER COLUMN id SET DEFAULT nextval('routes_id_seq'::regclass);


--
-- TOC entry 2083 (class 2604 OID 64736)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY stops ALTER COLUMN id SET DEFAULT nextval('stops_id_seq'::regclass);


--
-- TOC entry 2084 (class 2604 OID 64737)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- TOC entry 2085 (class 2604 OID 64738)
-- Name: id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY transport ALTER COLUMN id SET DEFAULT nextval('transport_id_seq'::regclass);


--
-- TOC entry 2086 (class 2604 OID 64739)
-- Name: user_id; Type: DEFAULT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY users ALTER COLUMN user_id SET DEFAULT nextval('users_user_id_seq'::regclass);


--
-- TOC entry 2089 (class 2606 OID 64742)
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 64744)
-- Name: adminemails_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY admin_emails
    ADD CONSTRAINT adminemails_pkey PRIMARY KEY (id);


--
-- TOC entry 2093 (class 2606 OID 64746)
-- Name: alerts_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY alerts
    ADD CONSTRAINT alerts_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 97156)
-- Name: attendance_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY attendance
    ADD CONSTRAINT attendance_pkey PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 97444)
-- Name: bus_gps_data_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY bus_gps_data
    ADD CONSTRAINT bus_gps_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2161 (class 2606 OID 97452)
-- Name: buses_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY buses
    ADD CONSTRAINT buses_pkey PRIMARY KEY (bus_id);


--
-- TOC entry 2095 (class 2606 OID 64750)
-- Name: daily_bus_coordinates_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_bus_coordinates
    ADD CONSTRAINT daily_bus_coordinates_pkey PRIMARY KEY (id);


--
-- TOC entry 2097 (class 2606 OID 64752)
-- Name: daily_bus_notification_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_bus_notification
    ADD CONSTRAINT daily_bus_notification_pkey PRIMARY KEY (id);


--
-- TOC entry 2099 (class 2606 OID 64754)
-- Name: daily_bus_stops_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_bus_stops
    ADD CONSTRAINT daily_bus_stops_pkey PRIMARY KEY (id);


--
-- TOC entry 2101 (class 2606 OID 64756)
-- Name: daily_driver_data_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_driver_data
    ADD CONSTRAINT daily_driver_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2103 (class 2606 OID 64758)
-- Name: daily_driver_speed_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_driver_speed
    ADD CONSTRAINT daily_driver_speed_pkey PRIMARY KEY (id);


--
-- TOC entry 2105 (class 2606 OID 64760)
-- Name: daily_running_busses_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_running_busses
    ADD CONSTRAINT daily_running_busses_pkey PRIMARY KEY (id);


--
-- TOC entry 2107 (class 2606 OID 64762)
-- Name: daily_subscriber_data_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY daily_subscriber_data
    ADD CONSTRAINT daily_subscriber_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2109 (class 2606 OID 64764)
-- Name: dailyrunningbusesjspentity_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY dailyrunningbusesjspentity
    ADD CONSTRAINT dailyrunningbusesjspentity_pkey PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 64766)
-- Name: eta_process_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY eta_process
    ADD CONSTRAINT eta_process_pkey PRIMARY KEY (id);


--
-- TOC entry 2157 (class 2606 OID 97329)
-- Name: gps_data_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY gps_data
    ADD CONSTRAINT gps_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2115 (class 2606 OID 64768)
-- Name: guardians_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY guardians
    ADD CONSTRAINT guardians_pkey PRIMARY KEY (id);


--
-- TOC entry 2111 (class 2606 OID 64770)
-- Name: id_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY drivers
    ADD CONSTRAINT id_pkey PRIMARY KEY (id);


--
-- TOC entry 2117 (class 2606 OID 64772)
-- Name: parents_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY parents
    ADD CONSTRAINT parents_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 64774)
-- Name: plexus_mediator_record_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY plexus_mediator_record
    ADD CONSTRAINT plexus_mediator_record_pkey PRIMARY KEY (id);


--
-- TOC entry 2155 (class 2606 OID 97172)
-- Name: price_fare_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY price_fare
    ADD CONSTRAINT price_fare_pkey PRIMARY KEY (fare_id);


--
-- TOC entry 2121 (class 2606 OID 64776)
-- Name: rfid_cards_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY rfid_cards
    ADD CONSTRAINT rfid_cards_pkey PRIMARY KEY (id);


--
-- TOC entry 2123 (class 2606 OID 64778)
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- TOC entry 2125 (class 2606 OID 64780)
-- Name: route_stops_list_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY route_stops_list
    ADD CONSTRAINT route_stops_list_pkey PRIMARY KEY (id);


--
-- TOC entry 2127 (class 2606 OID 64782)
-- Name: routes_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY routes
    ADD CONSTRAINT routes_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 97164)
-- Name: session_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY session
    ADD CONSTRAINT session_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 64784)
-- Name: settings_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 64786)
-- Name: staff_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY staff
    ADD CONSTRAINT staff_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 64788)
-- Name: staffjspentity_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY staffjspentity
    ADD CONSTRAINT staffjspentity_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 64790)
-- Name: stops_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY stops
    ADD CONSTRAINT stops_pkey PRIMARY KEY (id);


--
-- TOC entry 2137 (class 2606 OID 64792)
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- TOC entry 2139 (class 2606 OID 64794)
-- Name: studentsjspentity_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY studentsjspentity
    ADD CONSTRAINT studentsjspentity_pkey PRIMARY KEY (id);


--
-- TOC entry 2141 (class 2606 OID 64796)
-- Name: transport_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY transport
    ADD CONSTRAINT transport_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 88964)
-- Name: trip_history_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY trip_history
    ADD CONSTRAINT trip_history_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 64798)
-- Name: trips_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY trips
    ADD CONSTRAINT trips_pkey PRIMARY KEY (id);


--
-- TOC entry 2145 (class 2606 OID 64800)
-- Name: uk_g1uebn6mqk9qiaw45vnacmyo2; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT uk_g1uebn6mqk9qiaw45vnacmyo2 UNIQUE (user_id);


--
-- TOC entry 2163 (class 2606 OID 97460)
-- Name: user_gps_data_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY user_gps_data
    ADD CONSTRAINT user_gps_data_pkey PRIMARY KEY (id);


--
-- TOC entry 2147 (class 2606 OID 64802)
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: easycommute; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2164 (class 2606 OID 64808)
-- Name: fk_7ntjmnsh3jusajdfqatv79vqy; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY guardians
    ADD CONSTRAINT fk_7ntjmnsh3jusajdfqatv79vqy FOREIGN KEY (sms_alert_id) REFERENCES alerts(id);


--
-- TOC entry 2171 (class 2606 OID 97464)
-- Name: fk_kywhc76shp7900ia4yf0bj7ov; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY buses
    ADD CONSTRAINT fk_kywhc76shp7900ia4yf0bj7ov FOREIGN KEY (driverid) REFERENCES drivers(id);


--
-- TOC entry 2168 (class 2606 OID 64823)
-- Name: fk_nkap3owyj8ehwgenv2eupoi1k; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY trips
    ADD CONSTRAINT fk_nkap3owyj8ehwgenv2eupoi1k FOREIGN KEY (routeid) REFERENCES routes(id);


--
-- TOC entry 2166 (class 2606 OID 64828)
-- Name: fk_routeid; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY route_stops_list
    ADD CONSTRAINT fk_routeid FOREIGN KEY (route_id) REFERENCES routes(id);


--
-- TOC entry 2167 (class 2606 OID 64833)
-- Name: fk_stopid; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY route_stops_list
    ADD CONSTRAINT fk_stopid FOREIGN KEY (stop_id) REFERENCES stops(id);


--
-- TOC entry 2165 (class 2606 OID 64838)
-- Name: fk_ynjucfb7eo9c290wtvcfg6ik; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY guardians
    ADD CONSTRAINT fk_ynjucfb7eo9c290wtvcfg6ik FOREIGN KEY (email_alert_id) REFERENCES alerts(id);


--
-- TOC entry 2169 (class 2606 OID 64843)
-- Name: user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(role_id);


--
-- TOC entry 2170 (class 2606 OID 64848)
-- Name: user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: easycommute
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(user_id);


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: easycommute
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM easycommute;
GRANT ALL ON SCHEMA public TO easycommute;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-10-05 16:56:53 IST

--
-- easycommuteQL database dump complete
--

