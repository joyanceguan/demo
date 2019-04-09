upstream www.dgjs.com {
    sticky;
    server 47.92.166.52:8080 weight=2;  
    server 47.93.30.182:8080 weight=1;  
}  
