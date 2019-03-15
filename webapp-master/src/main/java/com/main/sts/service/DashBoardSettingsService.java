package com.main.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.DashBoardSettingsDao;
import com.main.sts.dto.response.AboutUsDTO;
import com.main.sts.dto.response.OfferCheck;
import com.main.sts.dto.response.ReferralDTO;
import com.main.sts.dto.response.VersionCheckDTO;
import com.main.sts.dto.response.VersionCheckResponseDTO;
import com.main.sts.entities.DashboardSettings;
import com.main.sts.util.MD5PassEncryptionClass;

@Service
public class DashBoardSettingsService {

    private final static int id = 1;

    @Autowired
    private MD5PassEncryptionClass password;

    @Autowired
    private DashBoardSettingsDao boardSettingsDao;

    public DashboardSettings getDashBoardSettings() {
        return boardSettingsDao.getDashBoardSettings(id);
    }

    public void updateSettings(DashboardSettings dashboardSettings) {
        String encrypt = password.EncryptText(dashboardSettings.getSmtp_password());
        dashboardSettings.setSmtp_password(encrypt);
        dashboardSettings.setId(id);
        if (getDashBoardSettings() == null) {
            boardSettingsDao.insertEntity(dashboardSettings);
        } else
            boardSettingsDao.updateSettings(dashboardSettings);
    }

    public AboutUsDTO getAboutUs() {
        DashboardSettings settings = boardSettingsDao.getDashBoardSettings(id);
        AboutUsDTO aboutus = new AboutUsDTO();
        aboutus.about_us_desc = settings.getAbout_us();
        aboutus.customer_care_email = settings.getCustomer_care_email();
        aboutus.customer_care_number = settings.getCustomer_care_number();
        aboutus.whatsapp_care_number = settings.getWhatsapp_care_number();
        return aboutus;
    }

    public String getReferralScheme() {
        DashboardSettings settings = boardSettingsDao.getDashBoardSettings(id);
        return settings.getReferral_scheme_desc();
    }
    
    public VersionCheckResponseDTO checkAppVersion(VersionCheckDTO version) {
        // 1.6 => 1 is major version and .6 is minor version
        String user_app_version = version.getApp_version();
        System.out.println("user_app_version:"+user_app_version);
        if (user_app_version == null) {
            throw new IllegalArgumentException("User app version shouldnt be null");
        }
        String[] user_app_version_arr = user_app_version.split("\\.");
        int user_app_major = Integer.parseInt(user_app_version_arr[0]);
        int user_app_min = Integer.parseInt(user_app_version_arr[1]);
        
        DashboardSettings settings = boardSettingsDao.getDashBoardSettings(id);
        String currentVersion =settings.getCurrent_app_version();
        VersionCheckResponseDTO version_check = new VersionCheckResponseDTO();
        
        String[] current_version_arr = currentVersion.split("\\.");
        int current_app_major = Integer.parseInt(current_version_arr[0]);
        int current_app_min = Integer.parseInt(current_version_arr[1]);

        // 1. if user's version is older than the current_app's major version
        // then force him to update app
        // 2. if user's version is same as currnet app's major version but
        // user's
        // app minor version is less than the current app's minor version then
        // dont force but suggest to download,
        // 3. if user's app major/minor version are in sync with the new version
        // then dont give him the update popup message
        if (current_app_major > user_app_major) {
            version_check.is_updated = true;
            version_check.force_to_update = true;
        } else if (current_app_major == user_app_major) {
            // if the current app version increases
            if (current_app_min > user_app_min) {
                version_check.is_updated = true;
            } else {
                version_check.is_updated = false;
            }
            version_check.force_to_update = false;
        } else {
            System.out.println("it shouldnt be a case where version in app is higher than version of current app");
        }
        
        version_check.version_check_update_message = settings.getVersion_check_update_message();
        version_check.current_app_version = currentVersion;
        return version_check;
    }
    
    public OfferCheck getOfferAnnouncementCheck() {
        DashboardSettings settings = boardSettingsDao.getDashBoardSettings(id);
        OfferCheck offerCheck = new OfferCheck();
        offerCheck.offer_message = settings.getOffer_announcement_message();
        offerCheck.enabled_offer_check = settings.isOffer_announcement_enabled();
        return offerCheck;
    }
}
