package belov.vlad.dapp.services;

import belov.vlad.dapp.model.ManufacturingProcess;
import belov.vlad.dapp.repository.ManufacturingProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturingProcessServiceIml implements ManufacturingProcessService{
    @Autowired
    private final ManufacturingProcessRepository manufacturingProcessRepository;

    public ManufacturingProcessServiceIml(ManufacturingProcessRepository manufacturingProcessRepository) {
        this.manufacturingProcessRepository = manufacturingProcessRepository;
    }

    @Override
    public List<ManufacturingProcess> findAll() {
        return manufacturingProcessRepository.findAll();
    }
}
