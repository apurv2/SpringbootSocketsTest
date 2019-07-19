package meetme.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileService {

    public void compressFile(File source, File target) throws EncoderException, IOException {

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libvorbis");

        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");
        video.setFrameRate(30);
        video.setSize(new VideoSize(1280, 720));

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);
    }


    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public void cleanUp(File source, File target) {
        target.delete();
        source.delete();
    }
}
