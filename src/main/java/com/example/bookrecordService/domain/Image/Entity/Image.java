package com.example.bookrecordService.domain.Image.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = image)
public class Image extends BaseEntity{
}
