package com.example.Taller1.IntegrationTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Taller1QuinteroLuisa.Taller1QuinteroLuisaApplication;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1QuinteroLuisaApplication.class)
@SpringBootTest
public class SalesTerritoryIntegrationTest{

}
