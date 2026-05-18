package com.emcode.tabz.service.imp;

import com.emcode.tabz.dto.MerchantRequest;
import com.emcode.tabz.dto.MerchantResponse;
import com.emcode.tabz.model.Merchant;
import com.emcode.tabz.repository.MerchantRepo;
import com.emcode.tabz.service.MerchantService;
import com.emcode.tabz.util.MerchantMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Primary
public class MerchantServiceBasic implements MerchantService {

    private final MerchantRepo merchantRepo;

    private final MerchantMapper merchantMapper;

    public MerchantServiceBasic(MerchantRepo merchantRepo, MerchantMapper merchantMapper) {
        this.merchantRepo = merchantRepo;
        this.merchantMapper = merchantMapper;
    }

    @Override
    public MerchantResponse  getMerchantById(Long id) {
        Optional<Merchant> optionalMerchant = merchantRepo.findById(id);
        return optionalMerchant.map(merchantMapper::mapToResponse).orElseThrow(() ->
                new NoSuchElementException("No merchant found with id: " + id));
        // todo: make custom exception and global handler
    }

    @Override
    public Long createMerchant(MerchantRequest merchantRequest) {
        Merchant savedMerchant = merchantRepo.save(merchantMapper.mapToEntity(merchantRequest));
        return savedMerchant.getId(); // todo: change returntype to response
    }
}
