package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.model.ManufacturingProcess;


import java.util.List;

public interface ManufacturingProcessService {
    List<ManufacturingProcess> findAll();

    ManufacturingProcess findById(Long id);

    void update(Long id, ManufacturingProcess manufacturingProcess);

    void save(ManufacturingProcess manufacturingProcess);

    ManufacturingProcess findForConversion(String s);
}
