package belov.vlad.dapp.services;

import belov.vlad.dapp.model.FileData;

import java.util.List;
import java.util.Optional;

public interface FileDataService {
    public FileData saveFile(String fileName, byte[] fileData);
    public Optional<FileData> getFileById(Long id);
    FileData getFileDataById(Long id);
    public List<FileData> findAll();
//    public Long save(FileData fileData);
    public Long save(FileData fileData);
}
