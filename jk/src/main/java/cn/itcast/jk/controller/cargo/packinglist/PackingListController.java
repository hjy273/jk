package cn.itcast.jk.controller.cargo.packinglist;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.service.PackingListService;


@Controller
public class PackingListController {
	@Resource
	PackingListService packingListService;
	
	@RequestMapping("/cargo/packinglist/list.action")
	private String list(Model model){
		List<PackingList> dataList = packingListService.find(null);
		model.addAttribute("dataList", dataList);
		
		return "/cargo/packinglist/jPackingListList.jsp";
	}
	@RequestMapping("/cargo/packinglist/tocreate.action")
	public String tocreate(String[] id, Model model){			//出口报运的id集合
		//携带出口报运的id集合	//显示装箱和报运的关系
		model.addAttribute("divData", packingListService.getDivDataCreate(id));					
		
		return "/cargo/packinglist/jPackingListCreate.jsp";
	}
	
	@RequestMapping("/cargo/packinglist/insert.action")
	public String insert(PackingList packingList){
		packingListService.insert(packingList);
		
		return "redirect:/cargo/packinglist/list.action";
	}
	
	@RequestMapping("/cargo/packinglist/toupdate.action")
	public String toupdate(String id, Model model){
		PackingList obj = packingListService.get(id);
		model.addAttribute("obj", obj);
		
		String _s = packingListService.getDivDataUpdate(obj.getExportIds().split("\\|"), obj.getExportNos().split("\\|"));
		model.addAttribute("divData", _s);	
				
		return "/cargo/packinglist/jPackingListUpdate.jsp";
	}
	
	@RequestMapping("/cargo/packinglist/update.action")
	public String update(PackingList packingList){
		packingListService.update(packingList);
		
		return "redirect:/cargo/packinglist/list.action";
	}
	
	@RequestMapping("/cargo/packinglist/toview.action")
	public String toview(String id, Model model){
		PackingList obj = packingListService.get(id);
		model.addAttribute("obj", obj);
		
		model.addAttribute("divData", packingListService.getDivDataView(obj.getExportNos().split("\\|")));
		
		return "/cargo/packinglist/jPackingListView.jsp";
	}
	
	
	
}
