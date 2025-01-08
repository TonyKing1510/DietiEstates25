package it.unina.webtech;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import it.unina.webtech.model.Image;
import it.unina.webtech.service.ImageService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.Map;

@Path("upload")
public class UploadImage {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void uploadImage(Image image) {
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure= true;
        try{
            // Upload the image
            Map params1 = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true
            );
            System.out.println(image.getPath());
            Map uploadRest = cloudinary.uploader().upload(image.getPath(), params1);
            String publicId = uploadRest.get("public_id").toString();
            ImageService service = new ImageService();
            service.addImage(publicId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
