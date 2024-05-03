package com.project2.hct.dto;

import lombok.Data;

@Data
public class LiveroomDTO {
	private String liveClno;
	private String liveName;
	private String liveThumbnail;
	/*
	public LiveroomDTO(String liveClno, String liveName, String liveThumbnail) {
	       this.liveClno = liveClno;
	       this.liveName = liveName;
	       this.liveThumbnail = liveThumbnail;
	   }
	   */
	@Override
	   // 데이터가 잘 받아졌는지 확인을 위해 작성
	   public String toString() {
	       return "LiveroomDTO{" +
	               "liveClno='" + this.liveClno + '\'' +
	               ", liveName='" + this.liveName + '\'' +
	               ", liveThumbnail='" + this.liveThumbnail + '\'' +
	               '}';
	   }
}