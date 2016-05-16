var json_panel_ui={
		retCode:101,
		msg:'请求成功',
		data:{
			res_type:'1001',
			ui:{
				view_id:'0',
				parent_id:'-1',
				view_type:'9999',
				view_name:'VgContentLayout',
				view_width:'1080',
				view_height:'1920',
				bg_normal_color:'#ffffffff',
				bg_focus_color:'#ffffffff',
				view_of:['-1','-1','-1','-1'],
				view_paddings:['0','0','0','0'],
				view_margins:['0','0','0','0'],
				refs:[],
				validation:[],
				gravity:'center',
				orientation:'0',
				centers:['1','4','7'],
				action_type:'null',
				action:'null',
				actionLink:{},
				res_key:'null',
				childs:[{
					view_id:'1',
					parent_id:'-1',
					view_type:'9999',
					view_name:'VgContentLayout',
					view_width:'1080',
					view_height:'136',
					bg_normal_color:'#ffcccccc',
					bg_focus_color:'#ff4444ccc',
					view_of:['-1','-1','-1','-1'],
					view_paddings:['0','0','0','0'],
					view_margins:['0','0','0','0'],
					refs:[],
					validation:[],
					gravity:'center',
					orientation:'0',
					centers:['1','4','7'],
					action_type:'null',
					action:'null',
					actionLink:{},
					res_key:'null',
					childs:[{
							view_id:'2',
							parent_id:'1',
							view_type:'10003',
							view_name:'VgTextView',
							view_width:'156',
							view_height:'156',
							bg_normal_color:'#ff4444ff',
							bg_focus_color:'#cce6e6e6',
							view_of:['-1','-1','-1','-1'],
							view_paddings:['36','10','10','10'],
							view_margins:['0','0','0','0'],
							gravity:'center',
							centers:[],
							orientation:'0',
							refs:[],
							validation:[],
							action_type:'null',
							action:'null',
							actionLink:{},
							res_key:'null',
							text:'返回',
							input_type:'1',
							hint:'',
							text_size:'48',
							text_align:"0",
							text_color:'#ffffffff',
							childs:[]
						}]
					},{
						view_id:'3',
						parent_id:'1',
						view_type:'10001',
						view_name:'VgTextView',
						view_width:'256',
						view_height:'136',
						bg_normal_color:'#ffff0000',
						bg_focus_color:'#ffff0000',
						view_of:['-1','-1','-1','-1'],
						view_paddings:['10','10','10','10'],
						view_margins:['412','370','412','10'],
						gravity:'center',
						centers:[],
						orientation:'0',
						refs:[],
						validation:[],
						action_type:'null',
						action:'null',
						actionLink:{},
						res_key:'null',
						text:'汽修案例',
						input_type:'1',
						hint:'',
						text_size:'48',
						text_align:"0",
						text_color:'#ff00ff00',
						childs:[]
					}]
		},
		eventLink:[{
			event_type:'1002',
			view_id:'2',
			actionLink:[{
				actionType:'10008',
				url:'http://192.168.16.198:8083/',
				ref_ui:[],
				params:[
					'device_id=12345678',
					'code=panelUI'
				],
				modify:[]
			}]
		}]
	}
};
function panel(req,res,data){
	
	var string =JSON.stringify(json_panel_ui);
	res.end(string);
}
exports.panel=panel;