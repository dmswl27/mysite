package com.douzone.mysite.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="response")
public class XmlResult {
	private String result; 		 /* "success" or "fail"  */
	private GuestbookVo data;    /* if success, data set */
	private String message; 	 /* if fail, message set */
	
	private XmlResult() {
	}

	private XmlResult(GuestbookVo data) {
		result = "success";
		this.data = data; 
	}

	private XmlResult(String message) {
		result = "fail";
		this.message = message; 
	}

	public static XmlResult success(GuestbookVo data) {
		return new XmlResult(data);
	}

	public static XmlResult fail(String message) {
		return new XmlResult(message);
	}
	
	@XmlRootElement(name="data")
	public static class GuestbookVo {
		private long no;
		private String name;
		private String password;
		private String regdate;
		private String message;
		public long getNo() {
			return no;
		}
		public void setNo(long no) {
			this.no = no;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getpassword() {
			return password;
		}
		public void setpassword(String password) {
			this.password = password;
		}
		public String getRegdate() {
			return regdate;
		}
		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public GuestbookVo getData() {
		return data;
	}

	public void setData(GuestbookVo data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}