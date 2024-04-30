package com.elephant.elephant_bi.service.impl;

import com.elephant.elephant_bi.domain.pojo.SqlInfo;
import com.elephant.elephant_bi.domain.pojo.Workbook;
import com.elephant.elephant_bi.mapper.SqlInfoMapper;
import com.elephant.elephant_bi.mapper.WorkbookMapper;
import com.elephant.elephant_bi.service.WorkbookService;
import com.elephant.elephant_bi.utils.FileUtil;
import com.elephant.elephant_bi.utils.MathUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("WorkbookService")
public class WorkbookServiceImpl implements WorkbookService {
    @Autowired
    private WorkbookMapper workbookMapper;

    @Autowired
    private SqlInfoMapper sqlInfoMapper;

    @Override
    public Workbook insert(Workbook workbook) {
        String reg = "未命名sql(（[0-9]+）){0,1}\\.sql";
        if(workbook.getType()==1) {
            List<Workbook> workbooks = workbookMapper.selectByParentId(workbook.getParentId(), "name");
            List<String> names = workbooks.stream().map(item -> item.getName()).filter(item -> item.matches(reg)).collect(Collectors.toList());
            if (!names.contains("未命名sql.sql")) {
                workbook.setName("未命名sql.sql");
            }else{
                workbook.setName("未命名sql（" + (MathUtil.getMax(names) + 1) + "）.sql");
            }
            long timestamp = System.currentTimeMillis();
            workbook.setPath("/sql/" + workbook.getCreator()+"/"+timestamp+"_"+workbook.getName());
            FileUtil.createFile("/sql/" + workbook.getCreator(),timestamp+"_"+workbook.getName());
            workbookMapper.insert((workbook));
            SqlInfo sqlInfo = new SqlInfo();
            sqlInfo.setName(workbook.getName().substring(0,workbook.getName().length()-4));
            sqlInfo.setCreator(workbook.getCreator());
            sqlInfo.setCreateTime(workbook.getCreateTime());
            sqlInfo.setShareAnalysis(workbook.getShareAnalysis());
            sqlInfo.setDataOriginId(8);
            sqlInfo.setWorkbookId(workbookMapper.selectByParentIdByName(workbook).getId());
            sqlInfoMapper.insert(sqlInfo);
        }else{
            workbookMapper.insert((workbook));
        }

        return workbookMapper.selectByParentIdByName(workbook);
    }

    @Override
    public List<Workbook> selectByParentId(@Param("parentId") int parentId,@Param("sortColumn") String sortColumn) {
        return workbookMapper.selectByParentId(parentId,sortColumn);
    }

    @Override
    public void update(Workbook workbook) {
        workbookMapper.update(workbook);
    }

    @Override
    public boolean checkName(Workbook workbook) {
        List<Workbook> workbooks = workbookMapper.selectByParentId(workbook.getParentId(),"name");
        for (Workbook w:workbooks) {
            if(workbook.getId() != 0 && workbook.getName().equals(w.getName()) && workbook.getId() != w.getId()){
                return false;
            }else if(workbook.getId() == 0 && workbook.getName().equals(w.getName())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void delete(Workbook workbook) {
        if(workbook.getType()==0){
            deleteChildren(workbook.getId());
        }else{
            FileUtil.deleteFile(workbook.getPath());
            sqlInfoMapper.delete(workbook.getId());
        }
        workbookMapper.delete(workbook);
    }

    /**
     * 递归删除子文件和文件夹
     * @param id
     */
    public void deleteChildren(int id){
        List<Workbook> workbooks = workbookMapper.selectByParentId(id, "name");
        for (Workbook workbook:workbooks) {
            if(workbook.getType() == 0){
                deleteChildren(workbook.getId());
            }else{
                FileUtil.deleteFile(workbook.getPath());
                sqlInfoMapper.delete(workbook.getId());
            }
            workbookMapper.delete(workbook);
        }
    }
}
