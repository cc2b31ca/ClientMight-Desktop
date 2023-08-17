#include "App.h"

CvarList cvar;

LRESULT CALLBACK WndProc(HWND hwnd,UINT msg,WPARAM wparam,LPARAM lparam) {
	switch (msg)
	{
	case WM_CREATE:
		CreateWindow("button", "Start Client Might", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, cvar.window_wid / 3, 10, 120, 40, hwnd, (HMENU)Button1, cvar.windowhinstance, NULL);
		CreateWindow("button", "Debug Client Might", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, cvar.window_wid / 3, 60, 130, 40, hwnd, (HMENU)Button2, cvar.windowhinstance, NULL);
		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	case WM_COMMAND:
		switch (LOWORD(wparam))
		{
		case Button1:
			system("start jre/bin/javaw -jar ClientMight.jar Release");
			break;
		case Button2:
			system("start jre/bin/java -jar ClientMight.jar debug");
			break;
		}
		break;
	}
	return DefWindowProc(hwnd, msg, wparam, lparam);
}

int WINAPI WinMain(HINSTANCE hinstance,HINSTANCE lhinstance,LPSTR lpstr,int showcmd) {
	WNDCLASS window = { 0 };
	window.hIcon = LoadIconA(hinstance,MAKEINTRESOURCE(IDI_ICON1));
	window.lpfnWndProc = WndProc;
	window.hCursor = NULL;
	window.hInstance = hinstance;
	window.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
	window.lpszMenuName = NULL;
	window.lpszClassName = TEXT("MainWindow");
	window.cbClsExtra = 0;
	window.cbWndExtra = 0;
	window.style = CS_VREDRAW | CS_HREDRAW;
	
	RegisterClass(&window);

	cvar.window_wid = 400;
	cvar.window_hei = 200;

	HWND hwnd = CreateWindow(TEXT("MainWindow"),
		TEXT("Ñ¡ÔñÆô¶¯Ïî"),
		WS_OVERLAPPEDWINDOW^WS_THICKFRAME^WS_MAXIMIZEBOX,
		(GetSystemMetrics(SM_CXSCREEN) - (cvar.window_wid)) / 2,
		(GetSystemMetrics(SM_CYSCREEN) - (cvar.window_hei)) / 2,
		cvar.window_wid ,
		cvar.window_hei,
		NULL,
		NULL,
		hinstance,
		NULL);

	ShowWindow(hwnd, SW_SHOW);
	UpdateWindow(hwnd);

	cvar.windowhwnd = hwnd;
	cvar.windowhinstance = hinstance;

	MSG msg;
	while (GetMessage(&msg,NULL,0,0))
	{
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}

	return 0;
}