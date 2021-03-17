package com.gridnine.testing;

import java.util.List;
import java.time.*;

public class FilteringRule {
	private LocalDateTime date1 = null;
	private LocalDateTime  date2 = null;
	private Duration duration1 = null;
	private Duration duration2 = null;
	private GlobalFilterType globalFilterType;
	private SpecialFilterType specialFilterType;

	public FilteringRule(GlobalFilterType globalFilterType, SpecialFilterType specialFilterType, LocalDateTime date1) {
		this.globalFilterType = globalFilterType;
		this.specialFilterType = specialFilterType;
		this.date1 = date1;
	}
	public FilteringRule(GlobalFilterType globalFilterType, SpecialFilterType specialFilterType, LocalDateTime date1, LocalDateTime date2) {
		this.globalFilterType = globalFilterType;
		this.specialFilterType = specialFilterType;
		this.date1 = date1;
		this.date2 = date2;
	}
	public FilteringRule(GlobalFilterType globalFilterType, SpecialFilterType specialFilterType, Duration duration1) {
		this.globalFilterType = globalFilterType;
		this.specialFilterType = specialFilterType;
		this.duration1 = duration1;
	}
	public FilteringRule(GlobalFilterType globalFilterType, SpecialFilterType specialFilterType) {
		this.globalFilterType = globalFilterType;
		this.specialFilterType = specialFilterType;
	}

	public LocalDateTime getDate1() {
		return date1;
	}
	public void setDate1(LocalDateTime date1) {
		this.date1 = date1;
	}
	public LocalDateTime getDate2() {
		return date2;
	}
	public void setDate2(LocalDateTime date2) {
		this.date2 = date2;
	}
	public Duration getDuration1() {
		return duration1;
	}
	public void setDuration1(Duration duration1) {
		this.duration1 = duration1;
	}
	public Duration getDuration2() {
		return duration2;
	}
	public void setDuration2(Duration duration2) {
		this.duration2 = duration2;
	}
	public GlobalFilterType getGlobalFilterType() {
		return globalFilterType;
	}
	public void setGlobalFilterType(GlobalFilterType globalFilterType) {
		this.globalFilterType = globalFilterType;
	}
	public SpecialFilterType getSpecialFilterType() {
		return specialFilterType;
	}
	public void setSpecialFilterType(SpecialFilterType specialFilterType) {
		this.specialFilterType = specialFilterType;
	}
	
	
 //   public List<Flight> get
}
