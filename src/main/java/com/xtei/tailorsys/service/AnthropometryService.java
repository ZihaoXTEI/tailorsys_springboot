package com.xtei.tailorsys.service;

import com.xtei.tailorsys.model.Anthropometry;

import java.util.Date;

/**
 * FileName: AnthropometryService
 * Author: Li Zihao
 * Date: 2021/3/27 22:08
 * Description:
 */

public interface AnthropometryService {

    Anthropometry findAnthropometryById(Integer anthrId);

    int addAnthropometry(Integer customerId, String anthrNote, Date measureTime);

    int updateAnthropometry(Anthropometry anthropometry);
}
