package com.jecyhw.shared.request;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.InputStream;

/**
 * Created by jecyhw on 16-8-21.
 */
final public class MongoGridFS {
    /**
     * 保存文件到mongodb的GridFS
     * @param mongoTemplate spring的MongoTemplate
     * @param inputStream 文件
     * @param fileName 在GridFS中的文件名
     */
    static public void saveFile(MongoTemplate mongoTemplate, InputStream inputStream, String fileName) {
        GridFS gridFS = new GridFS(mongoTemplate.getDb());
        GridFSInputFile gfs = gridFS.createFile(inputStream);
        gfs.setFilename(fileName);
        gfs.save();
    }
}
