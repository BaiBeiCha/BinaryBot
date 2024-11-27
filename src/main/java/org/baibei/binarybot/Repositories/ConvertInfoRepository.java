package org.baibei.binarybot.Repositories;

import org.baibei.binarybot.Data.ConvertInfo;
import org.springframework.data.repository.CrudRepository;

public interface ConvertInfoRepository extends CrudRepository<ConvertInfo, Long> {

    ConvertInfo findById(long id);

    ConvertInfo findByChatId(String chatId);
}
