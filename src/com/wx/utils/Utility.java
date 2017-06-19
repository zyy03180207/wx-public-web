package com.wx.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.wx.model.SecqurityGroup;
import com.wx.model.VSecqurityGroup;

public class Utility {
	public static Prop prop = null;
	public static Map secMap = new HashMap();
	public static VSecqurityGroup secAllRoot = null;

	/** 上传文件路径 */
	public static String UPLOAD_PATH = null;

	public static final String CHANNELID_JIANLIAN = "1000000000";// 建联通道
	// 验卡通道参数
	public static final String PUBLIC_KEY = "30820122300d06092a864886f70d01010105000382010f003082010a0282010100a3533f07aea0eecd80e7e585b67502b956cf96e47a4a34b58ac475b744db0981baee1d4452f6bcc5a6b278d32bec038b8f71d44e87a0927dafd2115db92778ca37d3d22a46c458c38b305eb82f723b3f83f14298a65b376a042c278a3b1b55e687aa38b73dd966a73eb76a93f860a92bbf1ed9f03bd6e9538fbc752b8a94fb69b918f9a88e9688268b01b6f1fef8a8f05bde102b4b464578f210b2bd70e8a0573eba2b24871c4f5e98992be36a0ed6dc237064b5e190c5ed18cec5ee16de1f5c2f31ed50fb9be663f0ecd94b19302599b14ed2f9f96d41eba358c3fcacb5335be284837af61525f1143893a5f240b172fd5a4a1cfa57940b5b339cd0437ef1d10203010001";
	public static final String PRIVATE_KEY = "308204bd020100300d06092a864886f70d0101010500048204a7308204a302010002820101008d6a973ca5713f604f5880f7289dc546fa27f83bcf49be022215d9e82a59f9337eac0f61b0b3421f875bdcf67fe663b67669f74eab26e1bd3dadc4a248a9c1519c8d4b9df302e785efe4da9904cd0dac22d9b8e12c1ce878dc42d3245c1946bc2fe9c477fb6f3157f00b7fefbefb25f83014a590296b8a3936258fb1228741d5db9cc1513e2439cd615903e214182ade7052c3190c2b5d920a232d3b6ea27a35a75883d19143bdf12cc49bec4afc079c51d5f5a4521eb8fb5ec8260f19c021547ca0c7398c800dc3b23e8921c5be9203804175e02efa650b8c462cad78bcc6ac21b75a47bc2010817c38bc31bc598d0914a785235472beaa74e29db2a922ccd3020301000102820100181ada2aa13cbb83cc2296e10a50524735eec83651b00d2e49e7159ca8536bd1e63ea7e48bfd1241538e4a0cce62540ecf0263004ad636d64e0427a74de179ad52951715964da16f4823e74afbf183ea1a8aa823e1a40fd0422335e055be8d3b18a3ad34926c32a9fda5c829f290d6b906610e93932bdc84c201070c37897ca41934341ea5769f4409b3274533e98de98bbb220e97090e31799c5f241d7d110b01711d06d8b4f1b09771f2d66e1bac0f4a6e7ef5555a03c90dc5ca799774659010dec3a74e34b0d08c0076263786629badc08564218f403bcda117e90d52af98a71e53ef74c1925d642985f618391a2786ec3563a6622d215e1123b89c1614a902818100d2efdb999b85d4885a25c652e1143d16013122f2f2f218287f600ee3a7de0b9e1389b22fb6b715067b58a9f4a01df1b46d1c362c53b010266b80cf6c65290bef1dfb7466a14603e5599816f290b1ff52461721d7d790794fbb55822e5370f61eb98eade2e4cab9bc85a4f8f608c610a9cc844ea8e8d698ac594959c21404b22702818100aba0a70b50172035088083f93aca2332ef66f9c811f1c4384c8c25c7924eacc92853ad8a2a075299dd10af6ea6abe5069cfa9d94440589d8e11049907d0a77b8514436e319e27f43a42ec5d062e4253225a68844681c71e88d627ad3b99e1c0dedd4c662dabf4d4e44441a24297a15f1aba59f93909fc1e7649f34a326f1377502818045b2d9d614cc90e08921083a24834277fde6d58c7f783d414c6d5a1e213ebe89fa1cba930785d15db5ee232260b1e446e0f2bc77c3235f02c2ecea1d0004231c5cc4d4be80b598ddf4cfcb6d55206ba42ce2c1dd072e86d730a85d7e9f35a92d97d4ac6257852100490cacf2e70d433e4804c3d50e32b279e87ad33cccce8c6b02818100937a21f053aafb8ef6b650314a7645d6cfe701be0cc55e222e8cc32e01a7bf4bbf6e6011f859807e1c7ae8f4c11db6587f6dcf188776e6e3c949d09ceb05a2a86c949c417b8da863fc6200b33736ea4b7414ecc5335d35f869fd74a418bd274844d4f080238dfd24c2fe6e2422cbf8ad491cb1ca009d76e69a0d4012ee349abd028180427ea1798012292af2dcc295f1c513e05b729d24af8883514d135fe0ba7f0c14249b63ff0dcecb062ecfc346b69cbc96821c10e843e75d09e932d78b94e97339c8c84bed933198551c2f82155817e2637372b4b95cbe7310ab6b63946e00e4f76e2eefe4a817e39421b4801520193d4d2181d0b4a958cf9fdfff1094fe8704c0";
	public static final String CHANNEL_CUST_ID = "0000089269";
	public static final String CHARSET = "utf-8";

	// public static final String CHANNEL_URL =
	// "http://25.0.89.55:8122/cbapsas/service_entry.do";//测试url
	// public static final String CHANNEL_URL =
	// "http://cbaps.yiguanjinrong.yg/cbapsas/service_entry.do";

	// 父类型 BUS_BRAND品牌商 BUS_AGENT1代理商 BUS_AGENT2代理商 BUS_AGENT3代理商 BUS_AGENT4代理商
	// BUS_MER商户表 B_BRANCH分行
	/**品牌商*/
	public final static String BUS_BRAND = "BUS_BRAND";
	/**1级机构*/
	public final static String BUS_AGENT1 = "BUS_AGENT1";
	/**2级机构*/
	public final static String BUS_AGENT2 = "BUS_AGENT2";
	/**3级机构*/
	public final static String BUS_AGENT3 = "BUS_AGENT3";
	/**4级机构*/
	public final static String BUS_AGENT4 = "BUS_AGENT4";
	/**商户*/
	public final static String BUS_MER = "BUS_MER";
	/**分行*/
	public final static String BUS_BRANCH = "BUS_BRANCH";
	/**分行复审类型*/
	public final static String BUS_BRANCH_RE = "BUS_BRANCH_RE";//分行复审类型
	
	//商户状态类型
	/**终审通过，等待复审*/
	public final static String CHECK_CODE_REVIEW = "0";//已终审，等待复审
	/**添加商户已保存，等待上传资质*/
	public final static String CHECK_CODE_SAVE = "1";//添加商户已保存，等待上传资质
	/**已上转资质，等待初审*/
	public final static String CHECK_CODE_UPLOAD = "2";//已上转资质，等待初审
	/**初审通过，等待终审*/
	public final static String CHECK_CODE_AUDIT1 = "3";//初审通过，等待终审
	/**复审通过，等待报备*/
	public final static String CHECK_CODE_AUDIT2 = "4";//复审通过，等待报备
	/**报备成功*/
	public final static String CHECK_CODE_SYNCED = "5";//报备完成
	/**初审、终审、复审 失败*/
	public final static String CHECK_CODE_AUDIT_FAIL = "6";//初审、终审、复审 失败
	/**报备拒绝，同步失败*/
	public final static String CHECK_CODE_SYNC_FAIL = "7";//报备拒绝，同步失败
	/**关停*/
	public final static String CHECK_CODE_CLOSE = "8";//关停
	/**商户更改信息，等待审核*/
	public final static String CHECK_CODE_UPDATE = "9";//商户更改信息，等待审核


	//
	/**机构 用户组 id ，对应 cpospas_secqurity_group 中主键id*/
	public static final int AGENT_GROUP_ID = 7;// 代理商用户组id
	/**商户 用户组 id ，对应 cpospas_secqurity_group 中主键id*/
	public static final int MERCHANT_GROUP_ID = 9;// 商户用户组id
	/**分行复审 用户组 id ，对应 cpospas_secqurity_group 中主键id*/
	public static final int BRANCH_REVIEW_GROUP_ID = 16;//TEST 分行复审组id

	public static final String INSTITUTION_NO = "311";// test机构代码
	public static final String ACCEPT_INSTITUTION_NO = "03110001";// test外部收单机构编号
	
	public static final String AGENT_NUMBER="51200001";//间联外部收单机构编号
	public static final String PLATFORM_CODE="CloudPOS";//间联外部收单平台编号

	static {
		prop = PropKit.use("little_config.txt");
		UPLOAD_PATH = prop.get("FILE_URL");
	}

	public static String getTime() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = dateFormater.format(date);

		return time;
	}

	public static Map getGroupSec(SecqurityGroup g, String gid) {

		Map result = null;
		if (secMap.get(gid) != null) {
			result = (Map) secMap.get(gid);
		} else {
			result = g.sg.getGroupSecqurityAction(gid);
			secMap.put(gid, result);
		}

		return result;
	}

	public static VSecqurityGroup getGroupAll(SecqurityGroup g) {

		if (secAllRoot != null) {
			return secAllRoot;
		} else {
			secAllRoot = new VSecqurityGroup();
			secAllRoot.setSid("0");
			secAllRoot.setPpsid("1");
			Map mm = g.sg.getAllSecqurityAction();

			getChilds(secAllRoot, mm);
		}

		return secAllRoot;
	}

	public static boolean isNull(String v) {

		if (v != null && v != "")
			return false;
		return true;
	}

	public static void getChilds(VSecqurityGroup root, Map allmenu) {
		getCPPid(allmenu, root.getPpsid(), root.getChilds());
		Iterator ite = allmenu.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			VSecqurityGroup vg = (VSecqurityGroup) allmenu.get(key);
			String sid = vg.getSid();
			String ppid = vg.getPpsid();
			String pid = vg.getPsid();
			// System.out.println(pid + "-" + sid + "-" + "-" + ppid + "-" +
			// root.getSid() + "-" + vg.getPpsid());
			if (!ppid.equalsIgnoreCase("")) {
				getCPPid(allmenu, vg.getSid(), vg.getChilds());
			}
		}

	}

	public static void getCPPid(Map allmenu, String ppid, List l) {
		Iterator ite = allmenu.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			VSecqurityGroup vg = (VSecqurityGroup) allmenu.get(key);
			String sid = vg.getSid();
			String pid = vg.getPpsid();
			if (!ppid.equalsIgnoreCase("") && pid.equalsIgnoreCase(ppid)) {
				l.add(vg);

			}

		}
	}

	/**
	 * string2timestamp
	 * 
	 * @param asDate
	 * @param asPattern
	 * @return
	 */
	public static Timestamp convertStringToTimeStamp(String asDate, String asPattern) {
		java.sql.Timestamp lStamp = null;
		if (asDate == null || asDate.length() == 0)
			lStamp = new java.sql.Timestamp(System.currentTimeMillis());
		else {
			if (asPattern == null || asPattern.length() == 0) {
				try {
					lStamp = java.sql.Timestamp.valueOf(asDate);
				} catch (Exception e) {
					lStamp = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				try {
					SimpleDateFormat lFormat = new SimpleDateFormat(asPattern);
					lStamp = new java.sql.Timestamp(lFormat.parse(asDate).getTime());
				} catch (Exception e) {
					lStamp = new java.sql.Timestamp(System.currentTimeMillis());
				}
			}
		}
		return lStamp;
	}

	/**
	 * Timestamp2string
	 * 
	 * @param asDate
	 * @param asPattern
	 * @return
	 */
	public static String convertTimeStampToString(java.util.Date asDate, String asPattern) {
		if (asDate == null)
			return "";
		SimpleDateFormat lFormat = new SimpleDateFormat();
		if ((asPattern != null) && (asPattern.length() > 0))
			lFormat.applyPattern(asPattern);
		return lFormat.format(asDate);
	}

	/**
	 * 格式化日期
	 * 
	 * @param adDate
	 * @param asPattern
	 * @return
	 */
	public static String GetFormattedDate(java.sql.Date adDate, String asPattern) {
		String lstDate = "";
		if (adDate != null) {
			SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat();
			lSimpleDateFormat.applyPattern(asPattern);
			lstDate = lSimpleDateFormat.format(adDate);
		} else {
			lstDate = "";
		}
		return lstDate;
	}

	public static boolean isChildAction(VSecqurityGroup root, String action) {

		if (root != null && root.getChilds() != null && root.getChilds().size() > 0) {
			List<VSecqurityGroup> cs = root.getChilds();
			for (int i = 0; i < cs.size(); i++) {
				VSecqurityGroup onem = (VSecqurityGroup) cs.get(i);
				if (onem.getAction() != null && onem.getAction().equals(action)) {
					return true;
				}
			}
		}

		return false;
	}

	public static int getChildSize(VSecqurityGroup p) {
		int i = 0;
		if (p != null && p.getChilds().size() > 0) {
			i += p.getChilds().size();
			for (int f = 0; f < p.getChilds().size(); f++) {
				VSecqurityGroup tp = p.getChilds().get(f);

				// for(int z = 0 ; z < tp.getChilds().size() ; z++){
				//
				// VSecqurityGroup ttp = tp.getChilds().get(z);
				i += tp.getChilds().size();

				// }
			}

		}

		return i;

	}

	public static boolean isChilds(boolean pd, List<VSecqurityGroup> l, String action) {

		if (pd)
			return true;
		if (l != null && l.size() > 0) {
			for (int f = 0; f < l.size(); f++) {
				VSecqurityGroup nm = (VSecqurityGroup) l.get(f);
				if (nm.getAction().equals(action))
					return true;
				else {
					pd = isChilds(pd, nm.getChilds(), action);
				}
			}
		}

		return pd;
	}

	public static boolean isUserSecChild(List<VSecqurityGroup> l, Map userSecs) {

		for (int i = 0; i < l.size(); i++) {
			VSecqurityGroup onem = (VSecqurityGroup) l.get(i);
			if (userSecs.get(onem.getAction()) != null)
				return true;
		}

		return false;
	}

	public static boolean isUserSecChildByAction(String action, Map userSecs) {

		if (userSecs.get(action) != null)
			return true;
		return false;
	}

	public static String getMenuHtml(Map userAction, String action, Map userSecs) {

		VSecqurityGroup all = Utility.getGroupAll(new SecqurityGroup());
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"Menu\">");
		List<VSecqurityGroup> cs = all.getChilds();
		Collections.sort(cs, new Comparator<VSecqurityGroup>() {
			public int compare(VSecqurityGroup arg0, VSecqurityGroup arg1) {

				int i = 0;
				int f = 0;
				if (arg0 != null && arg0.getSeq() != null) {
					try {
						i = Integer.valueOf(arg0.getSeq());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						i = 0;
					}
				}

				if (arg1 != null && arg1.getSeq() != null) {
					try {
						f = Integer.valueOf(arg1.getSeq());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						f = 0;
					}
				}

				return arg0.getSeq().compareTo(arg1.getSeq());
			}
		});
		for (int i = 0; i < cs.size(); i++) {
			VSecqurityGroup onem = (VSecqurityGroup) cs.get(i);
			List<VSecqurityGroup> cc = onem.getChilds();
			Collections.sort(cc, new Comparator<VSecqurityGroup>() {
				public int compare(VSecqurityGroup arg0, VSecqurityGroup arg1) {

					int i = 0;
					int f = 0;
					if (arg0 != null && arg0.getSeq() != null) {
						try {
							i = Integer.valueOf(arg0.getSeq());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							i = 0;
						}
					}

					if (arg1 != null && arg1.getSeq() != null) {
						try {
							f = Integer.valueOf(arg1.getSeq());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							f = 0;
						}
					}

					return arg0.getSeq().compareTo(arg1.getSeq());
				}
			});
			if (onem.getMenuName() != null && (onem.getIsmenu() != null && onem.getIsmenu().endsWith("1"))
					&& isUserSecChild(cc, userSecs)) {

				boolean isc = false;
				if (onem.getChilds() != null) {
					isc = isChilds(false, onem.getChilds(), action);
				}

				if (isChildAction(onem, action) || isc) {
					sb.append("<li class=\"lr_menu\"><a href=\"#\" class=\"lr_current\"><i class=\"" + onem.getIcon()
							+ "\"></i><i class=\"icon\"></i><span>" + onem.getMenuName() + "</span></a>");
					sb.append("<ul class=\"lr_s_menu\">");
				} else {
					sb.append("<li class=\"lr_menu\"><a href=\"#\"><i class=\"" + onem.getIcon()
							+ "\"></i><i class=\"icon\"></i><span>" + onem.getMenuName() + "</span></a>");
					sb.append("<ul class=\"lr_s_menu none\">");
				}

				for (int f = 0; f < cc.size(); f++) {
					VSecqurityGroup nm = (VSecqurityGroup) cc.get(f);

					isc = false;
					if (nm.getChilds() != null) {
						isc = isChilds(false, nm.getChilds(), action);
					}
					boolean issecs = isUserSecChildByAction(nm.getAction(), userSecs);

					if (nm.getIsmenu() != null && nm.getIsmenu().endsWith("1") && issecs) {
						if ((nm.getAction() != null && nm.getAction().equals(action)) || isc)
							sb.append("<li><a href=\"/WX" + nm.getAction() + "\" class=\"lr_zc_current\">"
									+ nm.getMenuName() + "</a></li>");
						else
							sb.append(
									"<li><a href=\"/WX" + nm.getAction() + "\">" + nm.getMenuName() + "</a></li>");
					} else {
						System.out.println("" + nm.getIsmenu() + "/" + nm.getIsmenu().endsWith("1") + "/" + issecs + "/"
								+ nm.getAction());
						System.out.println("nm = " + nm.getMenuName());
					}
				}

				sb.append("</ul></li>");
			}
		}
		sb.append("</ul>");

		// sb.append("<li class=\"lr_menu\"><a href=\"#\"
		// class=\"lr_current\"><i class=\"icon-laptop\"></i><i
		// class=\"icon\"></i><span>商户管理</span></a>");
		// sb.append("<ul class=\"lr_s_menu\">");
		// sb.append("<li><a href=\"<%=request.getContextPath()%>/merChant\"
		// class=\"lr_zc_current\">商户审核查询</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/merChant/delete\">删除商户</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/merChant/change\">商户费率修改</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/merChant/userOpinion\">用户意见反馈</a></li>");
		// sb.append("</ul></li>");
		//
		// sb.append("<li class=\"lr_menu\"><a href=\"#\"><i
		// class=\"icon-folder-close-alt\"></i><i
		// class=\"icon\"></i><span>查询交易</span></a>");
		// sb.append("<ul class=\"lr_s_menu none\">");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/transRecord\">分润明细查询</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/transRecord/weChatLook\">微信交易流水</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/transRecord/reconeiliation\">微信对账查询</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/transRecord/branchRecon\">下游对账导出</a></li>");
		// sb.append("</ul></li>");
		//
		// sb.append("<li class=\"lr_menu\"><a href=\"#\"><i class=\"
		// icon-beaker\"></i><i class=\"icon\"></i><span>品牌商管理</span></a>");
		// sb.append("<ul class=\"lr_s_menu none\">");
		// sb.append("<li><a href=\"#\">品牌商新增</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/brand/auditingBrand\">品牌商审核</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/brand/queryBrand\">品牌商查询</a></li>");
		// sb.append("</ul></li>");
		//
		// sb.append("<li class=\"lr_menu\"><a href=\"#\"><i
		// class=\"icon-home\"></i><i
		// class=\"icon\"></i><span>代理商管理</span></a>");
		// sb.append("<ul class=\"lr_s_menu none\">");
		// sb.append("<li><a href=\"#\">代理商新增</a></li>");
		// sb.append("<li><a href=\"#\">代理商审核</a></li>");
		// sb.append("<li><a href=\"#\">代理商查询</a></li>");
		// sb.append("<li><a href=\"#\">生成二维码</a></li>");
		// sb.append("</ul></li>");
		//
		// sb.append("<li class=\"lr_menu\"><a href=\"#\"><i
		// class=\"icon-random\"></i><i
		// class=\"icon\"></i><span>系统管理</span></a>");
		// sb.append("<ul class=\"lr_s_menu none\">");
		// sb.append("<li><a href=\"#\">用户管理</a></li>");
		// sb.append("<li><a href=\"#\">密码初始化</a></li>");
		// sb.append("<li><a
		// href=\"<%=request.getContextPath()%>/sec\">权限组管理</a></li>");
		// sb.append("</ul></li>");

		System.out.println(">>\n" + sb.toString());

		return sb.toString();
	}

	public static String CreateNewId() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String Month = "";
		if (month < 10) {
			Month = "0" + month;
		} else {
			Month = month + "";
		}
		int date = c.get(Calendar.DATE);
		String Date = "";
		if (date < 10) {
			Date = "0" + date;
		} else {
			Date = date + "";
		}
		String Hour = "";
		int hour = c.get(Calendar.HOUR_OF_DAY);

		if (hour < 10) {
			Hour = "0" + hour;
		} else {
			Hour = hour + "";
		}
		String Minute = "";
		int minute = c.get(Calendar.MINUTE);
		if (minute < 10) {
			Minute = "0" + minute;
		} else {
			Minute = minute + "";
		}
		String Second = "";
		int second = c.get(Calendar.SECOND);
		if (second < 10) {
			Second = "0" + second;
		} else {
			Second = second + "";
		}
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		String id = "8" + year + Month + Date + Hour + Minute + Second + result;
		return id;
	}

	public static String encodeMD5Hex(String data) {
		return DigestUtils.md5Hex(data);
	}

	// 产生[start, end]之间的随机数
	// Add By Zhanglibo @ 2016-6-17
	private static long counter = 0;

	public static int getRandom(int start, int end) {
		Random rdm = new Random(System.currentTimeMillis() + (counter++));
		int r = Math.abs(rdm.nextInt()) % (end - start + 1);
		return r + start;
	}

	public static void main(String args[]) {
		
	}

	public static boolean isCanAddAgent(String type) {
		if (type.equals(Utility.BUS_AGENT1) || type.equals(Utility.BUS_AGENT2) || type.equals(Utility.BUS_AGENT3)
				|| type.equals(Utility.BUS_BRAND))
			return true;

		return false;
	}

	/**
	 * 判断文件夹是否存在,如果不存在则创建文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void isExist(String path) {
		File file = new File(path);

		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}

	}

	/**
	 * 文件重命名
	 * 
	 * @param oldname
	 * @param newname
	 */
	public static void reNameFile(String oldname, String newname) {

		try {
			FileUtils.copyFile(new File(oldname), new File(newname));

			// 删除原文件
			new File(oldname).deleteOnExit();

		} catch (IOException ioex) {
			ioex.printStackTrace();
		}

	}

	/**
	 * 生成随机码
	 * 
	 * @param length
	 *            长度
	 */
	public static String getCode(int length) {
		// length表示生成字符串的长度
		String base = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 文件重命名
	 * 
	 * @param extName<br>
	 *            文件后缀
	 */
	public static String getImgFileName(String extName) {

		return getCode(2) + System.currentTimeMillis() + "" + extName;
	}

	/**
	 * 判断日期字符串格式
	 * @param strdate 日期字符串
	 * @param pattern 日期格式
	 * 
	 * @return boolean 
	 * */
	public static boolean isValidDate(String strdate,String pattern) {
		       boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		       if (isNull(pattern)) {
				pattern = "yyyyMMdd";
			}
		        SimpleDateFormat format = new SimpleDateFormat(pattern);
		        try {
		// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		           format.setLenient(false);
		           format.parse(strdate);
		        } catch (Exception e) {
		           // e.printStackTrace();
		 // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		            convertSuccess=false;
		        } 
		        return convertSuccess;
		 }
	
	 /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 数字格式化
     * 用于生成商户号
     * */
	public static String formateInt(int i) {
		i+=4000;
		DecimalFormat df = new DecimalFormat(); 
		df. applyPattern("0000"); 
		return df.format(i+1);
	}

}
