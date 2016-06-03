var edittext_normal_color="#ffffcccc";
var edittext_focus_color="#ff0099cc";
function loadFindPassword(req,res,DATA){
	var json_user_find_pass_ui={
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
					refs:[],
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
							refs:[],
							validation:[],
							res_key:'null',
							childs:[
								{
									view_id:'13',
									parent_id:'12',
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
									refs:[],
									validation:[],
									scaleType:'2',
									imageUrl:'http://192.168.16.198:8080/bg_btn_back.png',
									res_key:'null',
									childs:[]
								},
								{
									view_id:'14',
									parent_id:'12',
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
									refs:[],
									validation:[],
									res_key:'null',
									text:'找回密码',
									input_type:'1',
									hint:'',
									text_size:'48',
									text_align:"0",
									text_color:'#ffffffff',
									childs:[]
								}
							]
							},
						{
							view_id:'1',
							parent_id:'0',
							visible:'1',
							view_type:'9999',
							view_name:'VgContentLayout',
							view_width:'1080',
							clickable:'0',
							view_height:'156',
							bg_normal_color:'#ffffffff',
							bg_focus_color:'#ffffffff',
							view_of:['-1','12','-1','-1'],
							view_paddings:['0','0','0','0'],
							view_margins:['0','470','0','0'],
							refs:[],
							validation:[],
							res_key:'null',
							childs:[
							{
								view_id:'2',
								parent_id:'1',
								visible:'1',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'180',
								view_height:'136',
								bg_normal_color:'#ffffffff',
								bg_focus_color:'#ffffffff',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['10','10','10','10'],
								view_margins:['10','10','10','10'],
								refs:[],
								validation:[],
								res_key:'null',
								text:'帐   号:',
								input_type:'1',
								hint:'',
								text_size:'48',
								text_align:"2",
								text_color:'#ff000000',
								childs:[]
							},
							{
								view_id:'3',
								parent_id:'1',
								visible:'1',
								view_type:'10002',
								view_name:'VgTextField',
								view_width:'954',
								view_height:'136',
								bg_normal_color:edittext_normal_color,
								bg_focus_color:edittext_focus_color,
								view_of:['2','-1','-1','-1'],
								view_paddings:['30','30','30','30'],
								view_margins:['20','20','20','20'],
								refs:[],
								validation:[
									{valid_id:'1001',msg:'不能为空'}
								],
								res_key:'phone',
								text:'18319388532',
								password:'0',
								text_align:"1",
								input_type:'1',
								hint:'请填写手机号',
								text_size:'48',
								text_color:'#ff000000',
								childs:[]
							}]
						},
						{
							view_id:'4',
							parent_id:'0',
							visible:'1',
							view_type:'9999',
							view_name:'VgContentLayout',
							view_width:'1080',
							clickable:'0',
							view_height:'156',
							bg_normal_color:'#ffffffff',
							bg_focus_color:'#ffffffff',
							view_of:['-1','1','-1','-1'],
							view_paddings:['0','0','0','0'],
							view_margins:['0','10','0','0'],
							refs:[],
							validation:[],
							res_key:'null',
							childs:[{
									view_id:'5',
									parent_id:'4',
									visible:'1',
									view_type:'10001',
									view_name:'VgTextView',
									view_width:'180',
									view_height:'136',
									bg_normal_color:'#ffffffff',
									bg_focus_color:'#ffffffff',
									view_of:['-1','-1','-1','-1'],
									view_paddings:['10','10','10','10'],
									view_margins:['10','10','10','10'],
									refs:[],
									validation:[{valid_id:'1001',msg:'不能为空'}],
									res_key:'null',
									text:'验证码:',
									input_type:'1',
									hint:'',
									text_size:'48',
									text_align:"2",
									text_color:'#ff000000',
									childs:[]
								},{
									view_id:'6',
									parent_id:'4',
									visible:'1',
									view_type:'10002',
									view_name:'VgTextField',
									view_width:'520',
									view_height:'136',
									bg_normal_color:edittext_normal_color,
									bg_focus_color:edittext_focus_color,
									view_of:['5','-1','-1','-1'],
									view_paddings:['30','30','30','30'],
									view_margins:['20','20','20','20'],
									refs:[],
									validation:[{valid_id:'1001',msg:'不能为空'}],
									res_key:'qcode',
									text:'123456',
									input_type:'2',
									password:'0',
									text_align:'1',
									hint:'请填写验证码',
									text_size:'48',
									text_color:'#ff000000',
									childs:[]
								},{
									view_id:'7',
									parent_id:'0',
									visible:'1',
									view_type:'10003',
									view_name:'VgButton',
									view_width:'290',
									view_height:'136',
									bg_normal_color:'#ffffcccc',
									bg_focus_color:'#ff0099cc',
									view_of:['6','-1','-1','-1'],
									view_paddings:['10','10','10','10'],
									view_margins:['20','20','20','20'],
									refs:['3_phone'],
									validation:[],
									res_key:'phone',
									text:'获取验证码',
									text_size:'48',
									text_align:'0',
									text_color:'#ff000000',
									childs:[]
								}]
							},
						{
							view_id:'8',
							parent_id:'0',
							visible:'1',
							view_type:'9999',
							view_name:'VgContentLayout',
							view_width:'1080',
							view_height:'156',
							clickable:'0',
							bg_normal_color:'#ffffffff',
							bg_focus_color:'#ffffffff',
							view_of:['-1','4','-1','-1'],
							view_paddings:['0','0','0','0'],
							view_margins:['0','0','0','0'],
							refs:[],
							validation:[],
							res_key:'null',
							childs:[{
								view_id:'9',
								parent_id:'8',
								visible:'1',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'180',
								view_height:'136',
								bg_normal_color:'#ffffffff',
								bg_focus_color:'#ffffffff',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['10','10','10','10'],
								view_margins:['10','10','10','10'],
								refs:[],
								validation:[],
								res_key:'null',
								text:'密   码:',
								input_type:'1',
								hint:'',
								text_size:'48',
								text_align:"2",
								text_color:'#ff000000',
								childs:[]
							},{
								view_id:'10',
								parent_id:'8',
								visible:'1',
								view_type:'10002',
								view_name:'VgTextField',
								view_width:'954',
								view_height:'136',
								bg_normal_color:edittext_normal_color,
								bg_focus_color:edittext_focus_color,
								view_of:['9','-1','-1','-1'],
								view_paddings:['30','30','30','30'],
								view_margins:['20','20','20','20'],
								refs:[],
								validation:[],
								res_key:'username',
								text:'18319388532',
								password:'1',
								text_align:"1",
								input_type:'1',
								hint:'请填写密码',
								text_size:'48',
								text_color:'#ff000000',
								childs:[]
							}]
						},
						{
							view_id:'11',
							parent_id:'0',
							visible:'1',
							view_type:'10003',
							view_name:'VgButton',
							view_width:'1080',
							view_height:'136',
							bg_normal_color:'#ffffcccc',
							bg_focus_color:'#ff0099cc',
							view_of:['-1','8','-1','-1'],
							view_paddings:['10','10','10','10'],
							view_margins:['20','20','20','20'],
							refs:['3_username','6_userpass','-1_deviceId'],
							validation:[],
							res_key:'',
							text:'提   交',
							text_size:'48',
							text_align:'0',
							text_color:'#ff000000',
							childs:[]
						}
					]
			},
			eventLink:[
				{
					event_type:'1002',
					view_id:'11',
					actionLink:[{
						actionType:'10001',
						text:'点击了注册按钮',
						params:[],
						modify:[]
					}]
				},
				{
					event_type:'1002',
					view_id:'7',
					actionLink:[{
						actionType:'10014',
						url:'http://192.168.16.198:8083/',
						show_type:'0',
						ref_ui:['3'],
						params:[
							'device_id=12345678',
							'code=codeAction'
						],
						modify:[],
						result:{
							onOk:[{
								event_type:'1000',
								actionLink:[{
									actionType:'10011',
									time:'10000',
									change_id:'7',
									params:[],
									modify:[{
										ref_id:'7',
										f_type:'10001',
										f_value:'重新获取'
									},{

									}]
								}]
							}],
							onFail:[{
								event_type:'1000',
								actionLink:[{
									actionType:'10001',
									text:'',
									params:[],
									modify:[{
										ref_id:'7',
										f_type:'1006',
										f_value:'1'
									},{
										ref_id:'7',
										f_type:'10001',
										f_value:'重新获取'
									}]
								}]
							}],
							onErr:[{
								event_type:'1000',
								actionLink:[{
									actionType:'10001',
									text:'',
									params:[],
									modify:[{
										ref_id:'7',
										f_type:'1006',
										f_value:'1'
									},{
										ref_id:'7',
										f_type:'10001',
										f_value:'重新获取'
									}]
								}]
							}]
						}

					}]
				},
				{
					event_type:'1002',
					view_id:'13',
					actionLink:[{
						actionType:'10008',
						url:'http://192.168.16.198:8083/',
						show_type:'0',
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
	var string =JSON.stringify(json_user_find_pass_ui);
	res.end(string);
}
exports.loadFindPassword=loadFindPassword;