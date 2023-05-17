package belov.vlad.dapp.services;

import belov.vlad.dapp.model.FileData;
import belov.vlad.dapp.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileDataServiceImpl implements FileDataService{
    @Autowired
    private final FileDataRepository fileDataRepository;

    public FileDataServiceImpl(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public FileData saveFile(String fileName, byte[] fileData) {
        FileData pdfFile = new FileData();
        pdfFile.setName(fileName);
        pdfFile.setData(fileData);
        return fileDataRepository.save(pdfFile);
    }

    @Override
    public Optional<FileData> getFileById(Long id) {
        return fileDataRepository.findById(id);
    }

    public List<FileData> findAll() {
        return fileDataRepository.findAll();
    }

    @Override
    public void save(FileData fileData) {
        fileDataRepository.save(fileData);
    }
}
