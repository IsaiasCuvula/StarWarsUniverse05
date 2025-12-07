### **1. Add planets**

```
add_planet Coruscant
add_planet Tatooine
add_planet Lothal
```

---

### **2. Create Jedi**

```
create_jedi Coruscant Yoda GRAND_MASTER 900 Green 2.0
create_jedi Coruscant Obi-Wan KNIGHT 38 Blue 2.0
create_jedi Tatooine Luke KNIGHT 23 Green 1.9
create_jedi Tatooine Anakin KNIGHT-ASPIRANT 19 Blue 2.0
create_jedi Lothal Ezra INITIATE 14 Blue 1.6
create_jedi Lothal Kanan KNIGHT 25 Green 1.9
```

---

### **3. Remove Jedi**

```
remove_jedi Luke Tatooine
```

---

### **4. Promote Jedi**

```
promote_jedi Ezra 1.2
```

---

### **5. Demote Jedi**

```
demote_jedi Anakin 0.8
```

---

### **6. Get Strongest Jedi**

```
get_strongest_jedi Coruscant
get_strongest_jedi Tatooine
```

---

### **7. Get Youngest Jedi**

```
get_youngest_jedi Coruscant KNIGHT
get_youngest_jedi Lothal INITIATE
```

---

### **8. Get Most Used Saber Color**

```
get_most_used_saber_color Coruscant KNIGHT
get_most_used_saber_color Lothal
```

---

### **9. Print Planet**

```
print_planet Coruscant
print_planet Tatooine
```

---

### **10. Print Jedi**

```
print_jedi Yoda
print_jedi Luke
```

---

### **11. Print Two Planets**

```
print_two_planets Coruscant Tatooine
print_two_planets Lothal Coruscant
```

---

### **12. Exit**

```
exit
```