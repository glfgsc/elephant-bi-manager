package com.elephant.elephant_bi.service;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;

public interface DBChangeService {
    boolean changeDb(DataOrigin dataOrigin) throws Exception;
    boolean testDb(DataOrigin dataOrigin) throws Exception;
}
