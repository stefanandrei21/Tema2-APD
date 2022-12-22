public class Input {
    String folderName;
    Integer numberOfThreads;

    public Input(String folderName, Integer numberOfThreads) {
        this.folderName = folderName;
        this.numberOfThreads = numberOfThreads;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Integer getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(Integer numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public String toString() {
        return "Input{" +
                "folderName='" + folderName + '\'' +
                ", numberOfThreads=" + numberOfThreads +
                '}';
    }
}
