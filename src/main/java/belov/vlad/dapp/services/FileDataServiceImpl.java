package belov.vlad.dapp.services;

import belov.vlad.dapp.model.FileData;
import belov.vlad.dapp.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class FileDataServiceImpl implements FileDataService{
    @Autowired
    private final FileDataRepository fileDataRepository;
    @PersistenceContext
    private EntityManager entityManager;
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
//    Подробности: Ошибочная строка содержит (11, 1.1.112, null, 2023-05-28, 1, 4, AWAITING_CONFIRMATION).

//    @Override
//    public FileData getFileDataById(Long id) {
//        String nativeQuery = "SELECT * FROM file_data WHERE id = 4 ";
//        Query query = entityManager.createNativeQuery(nativeQuery, FileData.class);
//        return (FileData) query.getSingleResult();
//    }

    public List<FileData> findAll() {
        return fileDataRepository.findAll();
    }
    public Long save(FileData fileData) {
        FileData savedFileData = fileDataRepository.save(fileData);
        return savedFileData.getId();
    }
//    @Override
//    public Long save(FileData fileData) {
//        fileDataRepository.addFileData(fileData.getData(), fileData.getName());
//        return fileData.getId();
//    }

    @Override
    public FileData getFileDataById(Long id) {
        return fileDataRepository.findById(id).orElse(null);
    }
}
