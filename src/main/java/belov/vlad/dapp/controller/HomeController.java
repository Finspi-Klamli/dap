package belov.vlad.dapp.controller;


import belov.vlad.dapp.model.User;
import belov.vlad.dapp.repository.UserRepository;
import belov.vlad.dapp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

