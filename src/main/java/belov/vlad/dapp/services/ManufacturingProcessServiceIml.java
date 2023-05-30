package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Equipment;
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

    @Override
    public ManufacturingProcess findById(Long id) {
        return manufacturingProcessRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, ManufacturingProcess manufacturingProcess) {
        ManufacturingProcess mp = manufacturingProcessRepository.findById(id).orElse(null);
        mp.setName(manufacturingProcess.getName());
        mp.setDescription(manufacturingProcess.getDescription());
        mp.setEquipment(manufacturingProcess.getEquipment());
        manufacturingProcessRepository.save(mp);
    }

    @Override
    public void save(ManufacturingProcess manufacturingProcess) {
        manufacturingProcessRepository.save(manufacturingProcess);
    }

    @Override
    public ManufacturingProcess findForConversion(String s) {
        return manufacturingProcessRepository.findAll().stream().filter(mp->{
            String string = mp.getEquipment().getName()+": " +mp.getName();
            return string.equals(s);
        }).findFirst().orElse(null);
    }
}
