package com.springboot.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.springboot.jpa.models.Color;
import com.springboot.jpa.models.Customer;
import com.springboot.jpa.models.Producer;
import com.springboot.jpa.models.Size;
import com.springboot.jpa.models.Type;
import com.springboot.jpa.repositories.ColorRepository;
import com.springboot.jpa.repositories.SizeRepository;
import com.springboot.jpa.service.CustomerService;
import com.springboot.jpa.service.ProducerService;
import com.springboot.jpa.service.TypeService;

@SpringBootApplication
public class ClothingShopManagerApplication implements CommandLineRunner {

	@Autowired
	private ColorRepository colorRepo;

	@Autowired
	private SizeRepository sizeRepo;

	@Autowired
	private TypeService typeService;

	@Autowired
	private ProducerService producerService;

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(ClothingShopManagerApplication.class, args);
		System.out.println("Running Project Successfully !");
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(org.springframework.boot.ApplicationRunner.class);
//	}

	public void run(String... args) throws Exception {
		// add color
		List<Color> listColors = new ArrayList<Color>();
		listColors.add(new Color("red"));
		listColors.add(new Color("black"));
		listColors.add(new Color("yellow"));
		listColors.add(new Color("blue"));
		listColors.add(new Color("darkcyan"));
		listColors.add(new Color("purple"));
		listColors.add(new Color("darkblue"));
		listColors.add(new Color("lightgray"));
//		colorRepo.saveAll(listColors);

		// add size
		List<Size> listSize = new ArrayList<Size>();
		listSize.add(new Size("M"));
		listSize.add(new Size("L"));
		listSize.add(new Size("S"));
		listSize.add(new Size("XL"));
		listSize.add(new Size("XXL"));
//		sizeRepo.saveAll(listSize);

		// add type
//		typeService.save(new Type("Qu???n d??i"));
//		typeService.save(new Type("??o S?? Mi"));
//		typeService.save(new Type("??o Thun"));
//		typeService.save(new Type("?????m"));
//		typeService.save(new Type("V??y Ng???n"));
//		typeService.save(new Type("Gi??y"));
//		typeService.save(new Type("Qu???n Ng???n"));

		// add producer
//		producerService.save(new Producer("May M???c T?????ng Duy - C??ng Ty TNHH May M???c T?????ng Duy",
//				"V??n ph??ng: 662/4 S?? V???n H???nh (n???i d??i), P. 12, Q. 10, Tp. H??? Ch?? Minh (TPHCM)", "0283812473"));
//		
//		producerService.save(new Producer("?????ng Ph???c ??o Vi???t - C??ng Ty TNHH May M???c V?? In ???n ??o Vi???t",
//				"166/3 Ph???m Ng?? L??o,P. Hi???p Th??nh,Tp. Th??? D???u M???t B??nh D????ng",
//				"0274390779"));
//		
//		producerService.save(new Producer("Th???i Trang Felegant Uniform - C??ng Ty TNHH Th???i Trang Felegant Uniform", "493/58 C??ch M???ng Th??ng T??m, P13, Qu???n 10 Tp. H??? Ch?? Minh (TPHCM)", "0909045785"));
//		producerService.save(new Producer("May M???c Nh??n Thanh - C??ng Ty TNHH S???n Xu???t Th????ng M???i V?? D???ch V??? Nh??n Thanh",
//				"S??? 48, Ng?? 123, ??. Ho??ng Qu???c Vi???t, Q. C???u Gi???y, H?? N???i", "0966994641"));
//		producerService.save(new Producer("May M???c V??nh T??i - C??ng Ty TNHH S???n Xu???t & Th????ng M???i V??nh T??i",
//				"351/7 L?? V??n S???, P. 13, Q. 3, Tp. H??? Ch?? Minh (TPHCM)", "090373811"));
//		producerService.save(new Producer("Gi??y Nam Vi???t - C??ng Ty TNHH Gi??y Nam Vi???t",
//				"S??? D17/4A ??inh ?????c Thi???n, ???p 4, X. B??nh Ch??nh, H. B??nh Ch??nh, Tp. H??? Ch?? Minh (TPHCM)", "0987838689"));

		// add Cus
//		customerService.save(new Customer("L?? V??n ", "Tri???t", "(028)39555635", "Levantriet123@gmail.com",
//				"Ph?????ng 7, qu???n 11, Tp.HCM", "Trietlv", "TrietlLe123"));
//
//		customerService.save(new Customer("?????ng Thanh ", "Duy", "(028)39239246",
//				"Duyngao113@gmail.com",
//				"Ph?????ng 8, Qu???n 5, Tp.HCM",
//				"Duydt",
//				"KidoDuy113"));
//
//		customerService.save(new Customer("??inh Anh D??ng", "D??ng", "(028)37925801",
//				"AnhDung6202@gmail.com",
//				"???p B???u Tre II, X?? T??n An H???i, Huy???n C??? Chi, Tp.HCM",
//				"Dungda",
//				"Anhdung6202"));
//
//		customerService.save(new Customer("Mai V?? Tr?????ng ", "V??", "(028)39481817",
//			"Truongvu000@gmail.com",
//			"Ph?????ng 12, Qu???n T??n B??nh, Tp.HCM",
//			"Vumvt",
//			"Truongvu854"));
//
//		customerService.save(new Customer("?????ng Linh ", "T??m", "(028)35511245",
//			"LinhTam61@gmail.com",
//			"Ph?????ng 7, Qu???n B??nh Th???nh, Tp.HCM",
//			"Tamdl",
//			"Tamdang1212"));
	}

}
