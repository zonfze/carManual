var edittext_normal_color="#ffffcccc";
var edittext_focus_color="#ff0099cc";
var json_login_ui_1={
		retCode:101,
		msg:'请求成功',
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
				clickable:'0',
				bg_normal_color:'#ffffffff',
				bg_focus_color:'#ffffffff',
				view_of:['-1','-1','-1','-1'],
				view_paddings:['0','0','0','0'],
				view_margins:['0','0','0','0'],
				ref_views:[],
				validation:[],
				res_key:'null',
				childs:[
					{
						view_id:'12',
						parent_id:'0',
						visible:'1',
						view_type:'20006',
						view_name:'VgTopActionBar',
						view_width:'1080',
						view_height:'144',
						bg_normal_color:'#ffD01109',
						bg_focus_color:'#ffD01109',
						view_of:['-1','-1','-1','-1'],
						view_paddings:['0','0','0','0'],
						view_margins:['0','0','0','0'],
						ref_views:[],
						validation:[],
						res_key:'null',
						childs:[
							{
								view_id:'13',
								parent_id:'1',
								visible:'1',
								view_type:'10005',
								view_name:'VgImageView',
								view_width:'144',
								view_height:'144',
								bg_normal_color:'#00000000',
								bg_focus_color:'#00000000',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['15','45','15','45'],
								view_margins:['0','0','0','0'],
								ref_views:[],
								validation:[],
								scaleType:'2',
								imageUrl:'http://192.168.16.198:8080/bg_btn_back.png',
								res_key:'null',
								childs:[]
							},
							{
								view_id:'14',
								parent_id:'1',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'256',
								visible:'1',
								view_height:'144',
								bg_normal_color:'#00000000',
								bg_focus_color:'#00000000',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['0','0','0','0'],
								view_margins:['412','0','412','0'],
								ref_views:[],
								validation:[],
								res_key:'null',
								text:'个人信息',
								input_type:'1',
								hint:'',
								text_size:'48',
								text_align:"0",
								text_color:'#ffffffff',
								childs:[]
							}
							]
					}

				]

			},
			eventLink:[
				{
					event_type:'1002',
					view_id:'9',
					actionLink:[{
						actionType:'10007',
						url:'http://192.168.16.198:8083/',
						show_type:'0',
						ref_ui:[],
						params:[
							'device_id=12345678',
							'code=findPass'
						],
						modify:[]
					}]
				},
				{
					event_type:'1002',
					view_id:'10',
					actionLink:[{
						actionType:'10007',
						show_type:'0',
						url:'http://192.168.16.198:8083/',
						ref_ui:[],
						params:[
							'device_id=12345678',
							'code=registerUI'
						],
						modify:[]
					}]
				},
				{
					event_type:'1002',
					view_id:'13',
					actionLink:[{
						actionType:'10008',
						show_type:'0',
						url:'http://192.168.16.198:8083/',
						ref_ui:[],
						params:[
							'device_id=12345678',
							'code=panelUI'
						],
						modify:[]
					}]
				}
			]
		}
};
function userInfo(req,res,data){

	var string =JSON.stringify(json_login_ui_1);
	res.end(string);
}
exports.userInfo=userInfo;