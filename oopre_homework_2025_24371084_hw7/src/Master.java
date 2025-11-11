public interface Master {
    public void addServant(Adventurer servant); // 添加一个下级

    public void removeServant(Adventurer servant);

    public void helpMe(); // 下级治疗自己
}
