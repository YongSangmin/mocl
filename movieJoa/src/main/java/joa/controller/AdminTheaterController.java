package joa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import joa.adminSchedule.model.ScheduleDTO;
import joa.adminTheater.model.TheaterDAO;
import joa.adminTheater.model.TheaterDTO;

@Controller
public class AdminTheaterController {
	
	@Autowired
	private TheaterDAO theaterDao;

	@RequestMapping("/theaterAddForm.do")
	public String theaterAddForm() {
		return "admin/adminTheater/adminTheater_theaterAdd";
	}
	
	@RequestMapping("/theaterAdd.do")
	public String seat(@RequestParam(value="width",defaultValue = "-1") int width , @RequestParam(value="height",defaultValue = "-1") int height,Map map,String choice_seat,@RequestParam(value="seats",required = false)String[][] seats,@RequestParam(value="seats_s",required = false)String seats_s,String theater) {
		String[] rows = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		if(seats_s==null) {
			seats = new String[height][width];
			for (int i=0; i<height; i++) {
				for(int j=0; j<width; j++) {
					String temp=rows[i]+(j+1)+"N";
					seats[i][j] = temp;
				}
			}
		}else if(seats_s!=null){
			int start=0;
			int le=0;
			int ri=0;
			seats=new String[height][width];
			for(int i=0;i<seats_s.length();i++) {
				if(seats_s.charAt(i)==',') {
					if(ri==0 && le==0) {
						seats[le][ri]=seats_s.substring(0,i);
						start=i+1;
					}else {
						seats[le][ri]=seats_s.substring(start,i);
						start=i+1;
					}
					ri++;
					if(ri==width) {
						le++;
						ri=0;
					}
				}
			}
			for(int i=0;i<seats.length;i++) {
				for(int j=0;j<seats[i].length;j++) {
					if(seats[i][j].equals(choice_seat)) {
						if(seats[i][j].charAt(seats[i][j].length()-1)!='N') {
							seats[i][j]+="N";
						}else if(seats[i][j].charAt(seats[i][j].length()-1)=='N'){
							seats[i][j]=seats[i][j].substring(0,seats[i][j].length()-1);
						}
					}
				}
			}
		}
		map.put("rows", rows);
		map.put("seats", seats);
		map.put("width",width);
		map.put("height",height);
		map.put("theater",theater);
		return "admin/adminTheater/adminTheater_theaterAddView";
	}
	
	@RequestMapping("/theaterAddSubmit.do")
	public ModelAndView theaterAddSubmit(String seats_s,TheaterDTO dto) {
		String[] rows = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		int start=0;
		int le=0;
		int ri=0;
		String[][] seats=new String[dto.getHeight()][dto.getWidth()];
		for(int i=0;i<seats_s.length();i++) {
			if(seats_s.charAt(i)==',') {
				if(ri==0 && le==0) {
					seats[le][ri]=seats_s.substring(0,i);
					start=i+1;
				}else {
					seats[le][ri]=seats_s.substring(start,i);
					start=i+1;
				}
				ri++;
				if(ri==dto.getWidth()) {
					le++;
					ri=0;
				}
			}
		}
		int ch=0;
		for (int i=0; i<dto.getHeight(); i++) {
			for(int j=0; j<dto.getWidth(); j++) {
				ch=0;
				if(i==0) {
					if(seats[i][j].charAt(seats[i][j].length()-1)=='N') {
						for(int y=0;y<dto.getHeight();y++) {
							if(seats[y][j].charAt(seats[y][j].length()-1)!='N') {
								ch=1;
								break;
							}
						}
						if(ch==0) {
							for(int z=0;z<dto.getHeight();z++) {
								for(int x=j+1;x<dto.getWidth();x++) {
									if(seats[z][x].charAt(seats[z][x].length()-1)!='N') {
										int num=Integer.parseInt(seats[z][x].substring(1,seats[z][x].length()));
										seats[z][x]=rows[z]+(num-1);
									}else if(seats[z][x].charAt(seats[z][x].length()-1)=='N'){
										int num=Integer.parseInt(seats[z][x].substring(1,seats[z][x].length()-1));
										seats[z][x]=rows[z]+(num-1)+"N";
									}
								}
							}
						}
					}
				}
			}
		}
		String result="";
		for (int i=0; i<dto.getHeight(); i++) {
			for(int j=0; j<dto.getWidth(); j++) {
				result+=seats[i][j]+",";
			}
		}
		dto.setSeat(result);
		
		int total=theaterDao.theaterAdd(dto);
		String msg=total>0?"?????? ?????? ??????":"?????? ?????? ??????";
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("msg",msg);
		mav.setViewName("admin/adminTheater/adminTheater_theaterAdd_ok");
		return mav;
	}
	
	@RequestMapping("/scheduleAddForm.do")
	public String scheduleAddForm(Map map) {
		Date now = new Date();	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nDate=format.format(now);
		map.put("nDate", nDate);
		return "admin/adminTheater/adminTheater_schedule_add";
	}
	
	@RequestMapping("/scheduleAdd.do")
	public ModelAndView scheduleAdd(ScheduleDTO dto){
		//session id city branch add
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/adminTheater/adminTheater_schedule_add");
		return mav;
	}
}
