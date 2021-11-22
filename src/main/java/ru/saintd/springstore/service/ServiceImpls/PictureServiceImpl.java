package ru.saintd.springstore.service.ServiceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.saintd.springstore.controller.repr.PictureRepr;
import ru.saintd.springstore.persist.repo.PictureRepository;
import ru.saintd.springstore.service.ServiceInterfaces.PictureService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Override
    public List<PictureRepr> findAll() {
        return pictureRepository.findAll().stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public PictureRepr findById(Long id) {
        return new PictureRepr(pictureRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(PictureRepr pR) {
//        pictureRepository.save(new Picture(pR.getName(), pR.getContentType(), pR.getPictureData()));
    }
}

