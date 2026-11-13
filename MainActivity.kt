package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFFD4AF37),
            secondary = Color(0xFFC5A059),
            background = Color(0xFF0B192C),
            surface = Color(0xFF1E3E62),
            onPrimary = Color.Black,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "موسوعة الجزائر والعالم", 
                        color = Color(0xFFD4AF37),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0B192C)
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "الصوت", tint = Color(0xFFD4AF37))
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "الإعدادات", tint = Color(0xFFD4AF37))
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0B192C)
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "الرئيسية", tint = Color(0xFFD4AF37)) },
                    label = { Text("الرئيسية", color = Color(0xFFD4AF37)) },
                    selected = selectedTab == "home",
                    onClick = { selectedTab = "home" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Flag, contentDescription = "الجزائر", tint = Color(0xFFD4AF37)) },
                    label = { Text("الجزائر", color = Color(0xFFD4AF37)) },
                    selected = selectedTab == "algeria",
                    onClick = { selectedTab = "algeria" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Public, contentDescription = "العالم", tint = Color(0xFFD4AF37)) },
                    label = { Text("العالم", color = Color(0xFFD4AF37)) },
                    selected = selectedTab == "world",
                    onClick = { selectedTab = "world" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "الرؤساء", tint = Color(0xFFD4AF37)) },
                    label = { Text("الرؤساء", color = Color(0xFFD4AF37)) },
                    selected = selectedTab == "presidents",
                    onClick = { selectedTab = "presidents" }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF0B192C), Color(0xFF1E3E62))
                    )
                )
        ) {
            when (selectedTab) {
                "home" -> HomeScreen(onNavigate = { selectedTab = it })
                "algeria" -> AlgeriaSectionScreen()
                "world" -> WorldSectionScreen()
                "presidents" -> PresidentsScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3E62)),
                border = BorderStroke(2.dp, Color(0xFFD4AF37)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🇩🇿 دليل الجزائر والعالم 🌍",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD4AF37),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "موسوعة جغرافية وتاريخية شاملة في متناول يدك",
                        fontSize = مقام() fontSizeOr14(14),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(350.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    MenuCard("الولايات والبلديات", Icons.Default.Map) { onNavigate("algeria") }
                }
                item {
                    MenuCard("دول العالم وعواصمها", Icons.Default.Public) { onNavigate("world") }
                }
                item {
                    MenuCard("رؤساء الجزائر", Icons.Default.Person) { onNavigate("presidents") }
                }
                item {
                    MenuCard("المسافات بين المدن", Icons.Default.Navigation) {}
                }
            }
        }
    }
}

fun مقام(): Int = 14
fun fontSizeOr14(v: Int): androidx.compose.ui.unit.TextUnit = 14.sp

@Composable
fun MenuCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3E62)),
        border = BorderStroke(1.5.dp, Color(0xFFD4AF37)),
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = title, tint = Color(0xFFD4AF37), modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AlgeriaSectionScreen() {
    val wilayas = listOf(
        "01. أدرار", "02. الشلف", "03. الأغواط", "04. أم البواقي", "05. باتنة",
        "06. بجاية", "07. بسكرة", "08. بشار", "09. البليدة", "10. البويرة",
        "11. تمنراست", "12. تبسة", "13. تلمسان", "14. تيارت", "15. تيزي وزو",
        "16. الجزائر العاصمة", "17. الجلفة", "18. جيجل", "19. سطيف", "20. سعيدة"
    )
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("الولايات الجزائرية الـ 58", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD4AF37))
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(wilayas) { wilaya ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3E62)),
                    border = BorderStroke(1.dp, Color(0xFFD4AF37)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = wilaya,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun WorldSectionScreen() {
    val countries = listOf("الجزائر", "فلسطين", "المملكة العربية السعودية", "مصر", "المغرب", "تونس", "فرنسا", "إسبانيا", "تركيا", "قطر")
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("دول العالم وعواصمها", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD4AF37))
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(countries) { country ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3E62)),
                    border = BorderStroke(1.dp, Color(0xFFD4AF37)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = country,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun PresidentsScreen() {
    val presidents = listOf(
        "أحمد بن بلة (1962 - 1965)",
        "هواري بومدين (1965 - 1978)",
        "الشاذلي بن جديد (1979 - 1992)",
        "محمد بوضياف (1992)",
        "الأمين زروال (1994 - 1999)",
        "عبد العزيز بوتفليقة (1999 - 2019)",
        "عبد المجيد تبون (2019 - إلى اليوم)"
    )
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("رؤساء الجزائر منذ الاستقلال", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD4AF37))
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(presidents) { president ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3E62)),
                    border = BorderStroke(1.dp, Color(0xFFD4AF37)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = president,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}