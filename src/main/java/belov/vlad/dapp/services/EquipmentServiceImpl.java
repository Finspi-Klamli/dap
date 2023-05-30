package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    EquipmentRepository equipmentRepository;
    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
    @Override
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    @Override
    public Equipment findByName(String name) {
        return equipmentRepository.findAll().stream().filter(equipment -> equipment.toString().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Equipment findForConversion(String string) {
        return findByName(string);
    }

    @Override
    public void update(Long id, Equipment equipment) {
        Equipment e = equipmentRepository.findById(id).orElse(null);
        e.setName(equipment.getName());
        e.setDescription(equipment.getDescription());
        equipmentRepository.save(e);
    }

}
