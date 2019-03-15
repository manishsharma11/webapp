package com.main.sts;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dto.response.VersionCheckDTO;
import com.main.sts.dto.response.VersionCheckResponseDTO;
import com.main.sts.service.DashBoardSettingsService;

public class DashboardSettingsServiceTest extends BaseTest {

    @Resource
    private DashBoardSettingsService service;

    @Test
    public void testCheckAppVersion() {
        VersionCheckDTO version = new VersionCheckDTO();
        version.setApp_version("1.1");
        VersionCheckResponseDTO resp = service.checkAppVersion(version);
        System.out.println(resp);
    }
}
