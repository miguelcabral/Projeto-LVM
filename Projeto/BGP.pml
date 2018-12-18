chan c01 = [1] of {byte,byte}; 
chan c02 = [1] of {byte,byte}; 
chan c12 = [1] of {byte,byte}; 
chan c21 = [1] of {byte,byte}; 
active proctype n0() { 
c01 ! 35, 0; 
c02 ! 9, 0; 
}
active proctype n1() { 
byte electNode;
byte x;
byte path;
byte paths[3]; 
byte costs[3]; 
costs[0] = 255; 
costs[1] = 255; 
costs[2] = 255; 
byte min;
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
 		:: path == 210 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 4 -> costs[2] = 4; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 35 -> costs[2] = 9; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
 					if
 						:: c12 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 210 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path; 
 			if
 			:: c12 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 9 -> costs[2] = 35; 
 			if
 				:: costs[electNode] > costs[2] -> electNode = 2; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 1 * 10^x + path 
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
byte x;
byte path;
byte paths[3]; 
byte costs[3]; 
costs[0] = 255; 
costs[1] = 255; 
costs[2] = 255; 
byte min;
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
 		:: path == 120 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 4 -> costs[1] = 35; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 35 -> costs[1] = 9; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
 	if
 		:: path == 120 -> 
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
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path; 
 			if
 			:: c21 ! costs[electNode], path 
 			fi
fi
 		:: else ->
 		if
 		:: x == 9 -> costs[1] = 4; 
 			if
 				:: costs[electNode] > costs[1] -> electNode = 1; 
 					x = 0;
 					min = path;
 					do
 						:: min == 0 -> break
 						:: min = (min - (min % 10))/10; x = x+1
 					od
 					path = 2 * 10^x + path 
 					if
 						:: c21 ! costs[electNode], path; 
 					fi
 			fi
 		fi
 	fi
od
}
ltl { <> [] ( len(c01)==0 && len(c02)==0 && len(c12)==0 && len(c21)==0 ) }