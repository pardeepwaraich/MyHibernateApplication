package trng.imcs.jpa.entities;

import java.io.Serializable;

public class ReportBean implements Serializable {
	long stdNo;
	String totalMarks;
	String address;
	public long getStdNo() {
		return stdNo;
	}
	public void setStdNo(long stdNo) {
		this.stdNo = stdNo;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (stdNo ^ (stdNo >>> 32));
		result = prime * result + ((totalMarks == null) ? 0 : totalMarks.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportBean other = (ReportBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (stdNo != other.stdNo)
			return false;
		if (totalMarks == null) {
			if (other.totalMarks != null)
				return false;
		} else if (!totalMarks.equals(other.totalMarks))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReportBean [stdNo=" + stdNo + ", totalMarks=" + totalMarks + ", address=" + address + "]";
	}
	
	
}
