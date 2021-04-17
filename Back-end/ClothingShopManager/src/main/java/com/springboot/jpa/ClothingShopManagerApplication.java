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
//		typeService.save(new Type("Quần dài"));
//		typeService.save(new Type("Áo Sơ Mi"));
//		typeService.save(new Type("Áo Thun"));
//		typeService.save(new Type("Đầm"));
//		typeService.save(new Type("Váy Ngắn"));
//		typeService.save(new Type("Giày"));
//		typeService.save(new Type("Quần Ngắn"));

		// add producer
//		producerService.save(new Producer("May Mặc Tường Duy - Công Ty TNHH May Mặc Tường Duy",
//				"Văn phòng: 662/4 Sư Vạn Hạnh (nối dài), P. 12, Q. 10, Tp. Hồ Chí Minh (TPHCM)", "0283812473"));
//		
//		producerService.save(new Producer("Đồng Phục áo Việt - Công Ty TNHH May Mặc Và In ấn áo Việt",
//				"166/3 Phạm Ngũ Lão,P. Hiệp Thành,Tp. Thủ Dầu Một Bình Dương",
//				"0274390779"));
//		
//		producerService.save(new Producer("Thời Trang Felegant Uniform - Công Ty TNHH Thời Trang Felegant Uniform", "493/58 Cách Mạng Tháng Tám, P13, Quận 10 Tp. Hồ Chí Minh (TPHCM)", "0909045785"));
//		producerService.save(new Producer("May Mặc Nhàn Thanh - Công Ty TNHH Sản Xuất Thương Mại Và Dịch Vụ Nhàn Thanh",
//				"Số 48, Ngõ 123, Đ. Hoàng Quốc Việt, Q. Cầu Giấy, Hà Nội", "0966994641"));
//		producerService.save(new Producer("May Mặc Vĩnh Tài - Công Ty TNHH Sản Xuất & Thương Mại Vĩnh Tài",
//				"351/7 Lê Văn Sỹ, P. 13, Q. 3, Tp. Hồ Chí Minh (TPHCM)", "090373811"));
//		producerService.save(new Producer("Giày Nam Việt - Công Ty TNHH Giày Nam Việt",
//				"Số D17/4A Đinh Đức Thiện, Ấp 4, X. Bình Chánh, H. Bình Chánh, Tp. Hồ Chí Minh (TPHCM)", "0987838689"));

		// add Cus
//		customerService.save(new Customer("Lê Văn ", "Triết", "(028)39555635", "Levantriet123@gmail.com",
//				"Phường 7, quận 11, Tp.HCM", "Trietlv", "TrietlLe123"));
//
//		customerService.save(new Customer("Đổng Thanh ", "Duy", "(028)39239246",
//				"Duyngao113@gmail.com",
//				"Phường 8, Quận 5, Tp.HCM",
//				"Duydt",
//				"KidoDuy113"));
//
//		customerService.save(new Customer("Đinh Anh Dũng", "Dũng", "(028)37925801",
//				"AnhDung6202@gmail.com",
//				"Ấp Bầu Tre II, Xã Tân An Hội, Huyện Củ Chi, Tp.HCM",
//				"Dungda",
//				"Anhdung6202"));
//
//		customerService.save(new Customer("Mai Võ Trường ", "Vũ", "(028)39481817",
//			"Truongvu000@gmail.com",
//			"Phường 12, Quận Tân Bình, Tp.HCM",
//			"Vumvt",
//			"Truongvu854"));
//
//		customerService.save(new Customer("Đặng Linh ", "Tâm", "(028)35511245",
//			"LinhTam61@gmail.com",
//			"Phường 7, Quận Bình Thạnh, Tp.HCM",
//			"Tamdl",
//			"Tamdang1212"));
	}

}
