package fr.fms;

import static org.hamcrest.CoreMatchers.containsString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import fr.fms.business.IBusinessImpl;
//import fr.fms.entities.Article;
//import fr.fms.web.ArticleController;

@AutoConfigureMockMvc
@SpringBootTest
class SpringShopControllersTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IBusinessImpl businessImpl;

    @Test
    void test_get_welcome() throws Exception {
    	when(businessImpl.great()).thenReturn("Hello, Mock");
    	
    	this.mvc.perform(get("/greating"))
    		.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content()
            .string(containsString("Hello, Mock")));
    }
    
//    @Test
//    public void test_get_articles() throws Exception {
//    	//................
//    	this.mvc.perform(get("/articles"))	//THEN    
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$[0].brand", is("Samsung")));
//    		//$    : pointe sur la racine de la structure JSON
//    		//[0]  : pour le 1er élément
//    		//brand: brand pour l'attribut
//    		//is   : correspond au résultat attendu
//    }
}
