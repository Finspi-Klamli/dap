package belov.vlad.dapp.repository;

import belov.vlad.dapp.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO file_data (data, name) VALUES (:data, :name)", nativeQuery = true)
    void addFileData(@Param("data") byte[] data, @Param("name") String fileName);

    @Query(value = "SELECT * FROM file_data", nativeQuery = true)
    List<FileData> getAllFileData();

    @Query(value = "SELECT * FROM file_data WHERE id = :id", nativeQuery = true)
    FileData getFileDataById(@Param("id") Long id);
}