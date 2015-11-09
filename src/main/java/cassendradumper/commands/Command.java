package cassendradumper.commands;

import java.util.List;

public class Command {
	
	private List<String> keys;
	private String tableName;
	private List<Command> nextStep;
	
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Command> getNextStep() {
		return nextStep;
	}
	public void setNextStep(List<Command> nextStep) {
		this.nextStep = nextStep;
	}

}
