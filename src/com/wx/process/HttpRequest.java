package com.wx.process;

import com.wx.config.Common;
import com.wx.utils.URLConnection;

public class HttpRequest implements Handle{

	@Override
	public String execute(String text, String url) {
		// TODO Auto-generated method stub
		byte[] res;
		try {
			res = URLConnection.postBinResource(url, text, Common.ENCODE, Common.timeOutInSeconds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		return new String(res);
	}

}
