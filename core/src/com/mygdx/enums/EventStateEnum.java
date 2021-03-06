package com.mygdx.enums;

public enum EventStateEnum {
	NOT_OPENED("not_opened"), OPENED("opened"), CLOSED("closed"), CLEARED("cleared"), ALWAYS_OPEN("always_open"), ING(
			"ing");
	private String eventStateString;

	EventStateEnum(String eventStateString) {
		this.eventStateString = eventStateString;
	}

	@Override
	public String toString() {
		return eventStateString;
	}

	public static EventStateEnum findEventStateEnum(String stringName) {
		for (EventStateEnum eventStateEnum : EventStateEnum.values())
			if (eventStateEnum.toString().equals(stringName))
				return eventStateEnum;
		return null;
	}
}