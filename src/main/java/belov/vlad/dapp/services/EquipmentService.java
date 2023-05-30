package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.model.TechnologicalCard;

import java.util.List;

public interface EquipmentService {
    List<Equipment> findAll();

    Equipment findById(Long id);

    void save(Equipment equipment);
    Equipment findByName(String name);
    Equipment findForConversion(String string);


    void update(Long id, Equipment equipment);
}
