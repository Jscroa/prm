/**
 * 
 */
package cn.prm.server.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.prm.server.bean.CurrUser;
import cn.prm.server.commons.Constants.DB_STATUS;
import cn.prm.server.commons.UUIDUtil;
import cn.prm.server.dao.ISequenceDao;
import cn.prm.server.entity.Sequence;
import cn.prm.server.exception.BusinessException;

/**
 * @Title: SequenceService.java<br>
 * @Package: cn.prm.server.service<br>
 * @Description: <br>
 * @author yyao<br>
 * @date 2016年11月15日 下午5:11:45<br>
 * @version v1.0<br>
 */
@Service(value = "sequenceService")
public class SequenceService {
    
    private static final Logger log = LoggerFactory.getLogger(SequenceService.class);
    
    @Autowired
    ISequenceDao sequenceDao;
    
    /** 
     * @Title: currentValue<br>
     * @Description: <br>
     * @param currUser
     * @param seqName
     * @return
     * @throws BusinessException
     */
    public int currentValue(CurrUser currUser,String seqName) throws BusinessException{
        if(seqName==null || "".equals(seqName)){
            throw new BusinessException("服务器错误");
        }
        Sequence sequence = sequenceDao.getByKey(seqName);
        if(sequence==null){
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sequence = new Sequence();
            sequence.setGuid(UUIDUtil.randomUUID());
            sequence.setStatus(DB_STATUS.STATUS_ACTIVE);
            sequence.setCreateTime(now);
            sequence.setModifyTime(now);
            if(currUser!=null){
                sequence.setCreateUser(currUser.getGuid());
                sequence.setModifyUser(currUser.getGuid());
            }
            sequence.setSeqKey(seqName);
            sequence.setSeqValue(0);
            sequenceDao.add(sequence);
        }
        return sequence.getSeqValue();
    }
    
    /** 
     * @param currUser 
     * @Title: next<br>
     * @Description: <br>
     * @param seqName
     * @return
     * @throws BusinessException 
     */
    public int nextValue(CurrUser currUser,String seqName) throws BusinessException{
        if(seqName==null || "".equals(seqName)){
            throw new BusinessException("服务器错误");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Sequence sequence = sequenceDao.getByKey(seqName);
        if(sequence==null){ // 空，增加一条记录
            sequence = new Sequence();
            sequence.setGuid(UUIDUtil.randomUUID());
            sequence.setStatus(DB_STATUS.STATUS_ACTIVE);
            sequence.setCreateTime(now);
            sequence.setModifyTime(now);
            if(currUser!=null){
                sequence.setCreateUser(currUser.getGuid());
                sequence.setModifyUser(currUser.getGuid());
            }
            sequence.setSeqKey(seqName);
            sequence.setSeqValue(0);
            sequenceDao.add(sequence);
        }
        sequence = sequenceDao.getByKey(seqName);
        if(sequence==null) {
            throw new BusinessException("服务器遇到无法解决的错误");
        }
        sequence.setSeqValue(sequence.getSeqValue() + sequence.getIncrement());
        sequence.setModifyTime(now);
        if(currUser!=null){
            sequence.setModifyUser(currUser.getGuid());
        }
        return sequence.getSeqValue();
    }
    
//    private Sequence initSeq(CurrUser currUser,String seqName){
//        Sequence sequence = new Sequence();
//        sequence.setGuid(UUIDUtil.randomUUID());
//        sequence.setStatus(DB_STATUS.STATUS_ACTIVE);
//        sequence.setCreateTime(createTime);
//    }
}
