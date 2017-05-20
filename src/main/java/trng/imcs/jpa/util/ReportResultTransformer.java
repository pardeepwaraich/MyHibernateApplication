package trng.imcs.jpa.util;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

import trng.imcs.jpa.entities.ReportBean;


public class ReportResultTransformer implements ResultTransformer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object transformTuple(Object[] paramArrayOfObject, String[] paramArrayOfString) {
		ReportBean rptBean = new ReportBean();
		
		rptBean.setStdNo((Long)paramArrayOfObject[0]);
		rptBean.setTotalMarks((String)paramArrayOfObject[1]);
		rptBean.setAddress((String)paramArrayOfObject[2]);
		
		return rptBean;
	}

	public List transformList(List paramList) {
		return paramList;
	}

}
