100 DEFINT A-Z
110 SCREEN 8
120 FOR I=0 TO 15
130 R=(I MOD 8)*32
140 B1=INT(I/8)
150 FOR J=0 TO 15
160 LINE (I*16,J*13)-(I*16+15,J*13+12),R+B1+(J MOD 8)*4+INT(J/8)*2,BF
170 NEXT J
180 NEXT I
190 A$=INPUT$(1)
