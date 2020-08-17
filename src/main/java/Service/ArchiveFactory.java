package Service;

public class ArchiveFactory {
    public ArchiveService getArchiveType(ArchiveType type) {
        ArchiveService toReturn = null;
        switch (type) {
            case ZIP:
                toReturn = new ZipArchiveService();
                break;

            case COPY:
                toReturn = new CopyFilesService();
                break;

            default:
                throw new IllegalArgumentException("Wrong ArchiveServiceType" + type);
        }
        return toReturn;
    }
}
