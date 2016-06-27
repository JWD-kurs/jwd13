package jwd.wafepa.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

@RestController
@RequestMapping(value="/api/activities")
public class ApiActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@PostConstruct
	public void билоКако(){
		activityService.save(new Activity("Swimming"));
		activityService.save(new Activity("Running"));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Activity>> getActivities(){
		List<Activity> activities = activityService.findAll();
		
		return new ResponseEntity<>(activities, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<Activity> getActivity(
			@PathVariable Long id){
		
		Activity activity = activityService.findOne(id);
		
		if(activity==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(activity, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<Activity> delete(
			@PathVariable Long id){
		
		Activity deleted = activityService.delete(id);
		
		if(deleted==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<Activity> addActivity(
			@RequestBody Activity activity){
		
		Activity persisted = activityService.save(activity);
		
			
		return new ResponseEntity<>(persisted, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<Activity> editActivity(
			@PathVariable Long id,
			@RequestBody Activity activity
			){
		
		Activity persisted = activityService.save(activity);
		
		return new ResponseEntity<>(persisted,HttpStatus.OK);
	}
	
}
