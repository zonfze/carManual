var json_ui_1={
	retCode:101,
	msg:'',
	data:{
		res_type:'1001',
		ui:{
			view_id:'0',
			parent_id:'-1',
			visible:'1',
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
				parent_id:'0',
				visible:'1',
				view_type:'10005',
				view_name:'VgImageView',
				view_width:'1080',
				view_height:'1920',
				bg_normal_color:'#ffffffff',
				bg_focus_color:'#ffffffff',
				view_of:['-1','-1','-1','-1'],
				view_paddings:['0','0','0','0'],
				view_margins:['0','0','0','0'],
				refs:[],
				validation:[],
				scaleType:'1',
				gravity:'center',
				orientation:'0',
				imageUrl:'http://192.168.16.198:8080/happy_new_year.png',
				action_type:'null',
				action:'null',
				actionLink:{
					event_type:'1001',
					time:'5000',
					actionLink:[{
						actionType:'10009',
						url:'http://192.168.16.198:8083/',
						params:[
							'device_id=12345678',
							'code=loginUI'
						]
					},{
						actionType:'10001',
						text:'进入登陆界面',
						params:[]
						
					}]
				},
				res_key:'null',
				childs:[]
			}]
		},
	    eventLink:[{
			event_type:'1001',
			time:'5000',
			actionLink:[{
				actionType:'10009',
				url:'http://192.168.16.198:8083/',
				params:[
					'deviceid=12345678',
					'code=loginUI'
				],
				modify:[]
			},{
				actionType:'10001',
				text:'进入登陆界面',
				params:[],
				modify:[]
			}]
		}]
	}
};


function welcomeUI(req,res,DATA){
	res.end(JSON.stringify(json_ui_1));
}
exports.welcomeUI=welcomeUI;