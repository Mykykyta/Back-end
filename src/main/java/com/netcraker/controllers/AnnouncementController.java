package com.netcraker.controllers;

import com.netcraker.model.Announcement;
import com.netcraker.model.Page;
import com.netcraker.services.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(methods={RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        Assert.notNull(announcementService, "AnnouncementService must not be null!");
        this.announcementService = announcementService;
    }

    @GetMapping("/announcements")
    public Page<Announcement> getAnnouncements() {

        return announcementService.getAnnouncements(0);
    }

}
