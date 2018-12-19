chan c01 = [1] of {byte,short}; 
chan c02 = [1] of {byte,short}; 
chan c12 = [1] of {byte,short}; 
chan c21 = [1] of {byte,short}; 
active proctype n0() { 
c01 ! 2, 0; 
c02 ! 4, 0; 
}
active proctype n1() { 
byte electNode;
short x;
short path;
short paths[3]; 
byte costs[3]; 
costs[0] = 255; 
costs[1] = 255; 
costs[2] = 255; 
short min;
do
 	:: c01 ? x, path; 
 		costs[0] = x;
 			if
 				:: costs[electNode] > costs[0] -> electNode = 0;
 					path = 1 * 10; 
 					if
 						:: c12 ! costs[electNode], path 
 					fi
 			fi
 	:: c21 ? x, path; 
 	if
 		:: path == 210 -> costs[2] = 255; 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 1 -> costs[2] = 3; paths[2] = path; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> costs[2] = 255; 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 2 -> costs[2] = 2; paths[2] = path; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> costs[2] = 255; 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 3 -> costs[2] = 4; paths[2] = path; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> costs[2] = 255; 
if
 			:: electNode == 2 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=2 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 2 -> path = paths[electNode]; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 4 -> costs[2] = 1; paths[2] = path; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 1; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
od
}
active proctype n2() { 
byte electNode;
short x;
short path;
short paths[3]; 
byte costs[3]; 
costs[0] = 255; 
costs[1] = 255; 
costs[2] = 255; 
short min;
do
 	:: c02 ? x, path; 
 		costs[0] = x;
 			if
 				:: costs[electNode] > costs[0] -> electNode = 0;
 					path = 2 * 10; 
 					if
 						:: c21 ! costs[electNode], path 
 					fi
 			fi
 	:: c12 ? x, path; 
 	if
 		:: path == 120 -> costs[1] = 255; 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 1 -> costs[1] = 1; paths[1] = path; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> costs[1] = 255; 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 2 -> costs[1] = 4; paths[1] = path; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> costs[1] = 255; 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 3 -> costs[1] = 2; paths[1] = path; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> costs[1] = 255; 
if
 			:: electNode == 1 -> 
 			x = 1;
 			min = costs[0];
 			do
 				:: x >= 3 -> break 
 				:: costs[x] < min && x!=1 -> min = costs[x]; electNode = x; x = x+1 
 				:: x = x+1
 			od
 		fi
 		if
 			:: electNode != 1 -> path = paths[electNode]; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 4 -> costs[1] = 3; paths[1] = path; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 2; 
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x*10
 					od
 					path = x + path;
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
od
}
ltl p1 { <> [] ( len(c01)==0 && len(c02)==0 && len(c12)==0 && len(c21)==0 ) }