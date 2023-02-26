package org.example.model;

public class Audio {
	private String singerName;
	private String audiotrackTitle;
	private String nameofalbumTitle;
	private int track_Number;
	private int year;
	private int reviewsCount;
	private int noofcopiesSold;
	
	public Audio(String singerName, String audiotrackTitle, String nameofalbumTitle, int trackNumber, int year,
			int reviewsCount, int noofcopiesSold ) {

		this.singerName = singerName;
		this.audiotrackTitle = audiotrackTitle;
		this.nameofalbumTitle = nameofalbumTitle;
		this.track_Number = trackNumber;
		this.year = year;
		this.reviewsCount=reviewsCount;
		this.noofcopiesSold = noofcopiesSold;
	}
	public Audio() {
		
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getAudiotrackTitle() {
		return audiotrackTitle;
	}
	public void setAudiotrackTitle(String audiotrackTitle) {
		this.audiotrackTitle = audiotrackTitle;
	}
	public String getNameofalbumTitle() {
		return nameofalbumTitle;
	}
	public void setNameofalbumTitle(String nameofalbumTitle) {
		this.nameofalbumTitle = nameofalbumTitle;
	}
	public int getTrack_Number() {
		return track_Number;
	}
	public void setTrack_Number(int track_Number) {
		this.track_Number = track_Number;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getReviewsCount() {
		return reviewsCount;
	}
	public void setReviewsCount(int reviewsCount) {
		this.reviewsCount = reviewsCount;
	}
	public int getNoofcopiesSold() {
		return noofcopiesSold;
	}
	public void setNoofcopiesSold(int noofcopiesSold) {
		this.noofcopiesSold = noofcopiesSold;
	}
}
	
	